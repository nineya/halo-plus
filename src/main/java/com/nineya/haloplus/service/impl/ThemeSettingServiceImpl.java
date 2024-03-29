package com.nineya.haloplus.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nineya.haloplus.model.enums.InputType;
import com.nineya.haloplus.utils.JsonUtils;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import com.nineya.haloplus.exception.ServiceException;
import com.nineya.haloplus.handler.theme.config.support.Group;
import com.nineya.haloplus.handler.theme.config.support.Item;
import com.nineya.haloplus.model.entity.ThemeSetting;
import com.nineya.haloplus.repository.ThemeSettingRepository;
import com.nineya.haloplus.service.ThemeService;
import com.nineya.haloplus.service.ThemeSettingService;
import com.nineya.haloplus.service.base.AbstractCrudService;
import com.nineya.haloplus.utils.ServiceUtils;

/**
 * Theme setting service implementation.
 *
 * @author johnniang
 * @date 2019-04-08
 */
@Slf4j
@Service
public class ThemeSettingServiceImpl extends AbstractCrudService<ThemeSetting, Integer>
    implements ThemeSettingService {

    private final ThemeSettingRepository themeSettingRepository;

    private final ThemeService themeService;

    private final Configuration configuration;

    public ThemeSettingServiceImpl(ThemeSettingRepository themeSettingRepository,
        ThemeService themeService,
        Configuration configuration) {
        super(themeSettingRepository);
        this.themeSettingRepository = themeSettingRepository;
        this.themeService = themeService;
        this.configuration = configuration;
    }

    @Override
    public ThemeSetting save(String key, String value, String themeId) {
        Assert.notNull(key, "Setting key must not be null");
        assertThemeIdHasText(themeId);

        log.debug("Starting saving theme setting key: [{}], value: [{}]", key, value);

        // Find setting by key
        Optional<ThemeSetting> themeSettingOptional =
            themeSettingRepository.findByThemeIdAndKey(themeId, key);

        if (StringUtils.isBlank(value)) {
            // Delete it
            return themeSettingOptional
                .map(setting -> {
                    themeSettingRepository.delete(setting);
                    log.debug("Removed theme setting: [{}]", setting);
                    return setting;
                }).orElse(null);
        }

        // Update or create
        ThemeSetting themeSetting = themeSettingOptional
            .map(setting -> {
                log.debug("Updating theme setting: [{}]", setting);
                setting.setValue(value);
                log.debug("Updated theme setting: [{}]", setting);
                return setting;
            }).orElseGet(() -> {
                ThemeSetting setting = new ThemeSetting();
                setting.setKey(key);
                setting.setValue(value);
                setting.setThemeId(themeId);
                log.debug("Creating theme setting: [{}]", setting);
                return setting;
            });
        // Determine whether the data already exists
        if (themeSettingRepository.findOne(Example.of(themeSetting)).isPresent()) {
            return null;
        }
        // Save the theme setting
        return themeSettingRepository.save(themeSetting);
    }

    @Override
    public void save(Map<String, Object> settings, String themeId) {
        assertThemeIdHasText(themeId);

        if (CollectionUtils.isEmpty(settings)) {
            return;
        }

        // Save the settings
        settings.forEach((key, value) -> save(key, value.toString(), themeId));

        try {
            configuration
                .setSharedVariable("settings", listAsMapBy(themeService.getActivatedThemeId()));
        } catch (TemplateModelException e) {
            throw new ServiceException("主题设置保存失败", e);
        }
    }

    @NonNull
    @Override
    public List<ThemeSetting> listBy(String themeId) {
        assertThemeIdHasText(themeId);

        return themeSettingRepository.findAllByThemeId(themeId);
    }

    @NonNull
    @Override
    public Map<String, Object> listAsMapBy(@NonNull String themeId) {
        // Convert to item map(key: item name, value: item)
        Set<Item> itemMap = getConfigItem(themeId);

        return listAsMapBy(themeId, itemMap);
    }

    @NonNull
    @Override
    public Map<String, Object> listAsMapBy(String themeId, String group) {
        // Convert to item map(key: item name, value: item)
        Set<Item> items = themeService.fetchConfigItemsBy(themeId, group);

        return listAsMapBy(themeId, items);
    }

    @NotNull
    private Map<String, Object> listAsMapBy(String themeId, Set<Item> items) {
        Assert.notNull(themeId, "The themeId must not be null.");
        Assert.notNull(items, "The itemMap must not be null.");

        // Get theme setting
        List<ThemeSetting> themeSettings = listBy(themeId);
        Map<String, ThemeSetting> themeSettingMap =
            ServiceUtils.convertToMap(themeSettings, ThemeSetting::getKey);

        Map<String, Object> result = new HashMap<>();

        items.forEach(item -> {
            String value = Optional.ofNullable(themeSettingMap.get(item.getName()))
                .map(ThemeSetting::getValue)
                .orElse(null);
            loadDefinedItem(result, item, value);
        });
        return result;
    }

    @Override
    @Transactional
    public void deleteInactivated() {
        themeSettingRepository.deleteByThemeIdIsNot(themeService.getActivatedThemeId());
    }

    private void loadDefinedItem(Map<String, Object> result, Item item, Object value) {
        String itemName = item.getName();
        log.debug("Name: [{}], item: [{}]", itemName, item);

        // default value
        if (value == null) {
            if (item.getDefaultValue() == null) {
                return;
            }
            if (item.getType() == InputType.REPEATER) {
                if (item.getChildren() == null || item.getChildren().isEmpty()) {
                    result.put(itemName, Collections.emptyList());
                    return;
                }
                List<Map<String, Object>> defaultRepeaterValue =
                    (List<Map<String, Object>>) item.getDefaultValue();

                List<HashMap<String, Object>> repeaterResult = new ArrayList<>();
                defaultRepeaterValue.forEach(repeaterItem -> {
                    HashMap<String, Object> childrenResult = new HashMap<>();
                    item.getChildren().forEach(childrenItem -> loadDefinedItem(
                        childrenResult, childrenItem, repeaterItem.get(childrenItem.getName())));
                    repeaterResult.add(childrenResult);
                });
                log.debug("Converted pre-defined data from [{}] to [{}], input-type: REPEATER",
                    defaultRepeaterValue, repeaterResult);
                result.put(itemName, repeaterResult);
                return;
            } else if (item.getType() == InputType.CHECKBOX) {
                List<Object> defaultCheckboxValue = (List<Object>) item.getDefaultValue();
                Set<Object> defaultCheckboxResult = defaultCheckboxValue.stream()
                    .map(val -> item.getDataType().convertTo(val))
                    .collect(Collectors.toSet());
                log.debug("Converted pre-defined data from [{}] to [{}], input-type: CHECKBOX",
                    defaultCheckboxValue, defaultCheckboxResult);
                result.put(itemName, defaultCheckboxResult);
                return;
            }
            Object convertedDefaultValue = item.getDataType().convertTo(item.getDefaultValue());
            log.debug("Converted pre-defined data from [{}] to [{}], type: [{}]",
                item.getDefaultValue(), convertedDefaultValue, item.getDataType());
            result.put(itemName, convertedDefaultValue);
            return;
        }

        // user defined value
        if (item.getType() == InputType.REPEATER) {

            ObjectMapper mapper = JsonUtils.createDefaultJsonMapper();
            TypeReference<List<HashMap<String, String>>> typeRef =
                new TypeReference<>() {
                };
            List<HashMap<String, String>> repeaterValue = null;
            try {
                repeaterValue = mapper.readValue(value.toString(), typeRef);
            } catch (JsonProcessingException e) {
                log.error("Converted user-defined data value [{}] JSON deserialization failed",
                    value, e);
                throw new RuntimeException(e);
            }

            List<HashMap<String, Object>> repeaterResult = new ArrayList<>();
            repeaterValue.forEach(repeaterItem -> {
                HashMap<String, Object> childrenResult = new HashMap<>();
                item.getChildren().forEach(childrenItem -> loadDefinedItem(
                    childrenResult, childrenItem, repeaterItem.get(childrenItem.getName())));
                repeaterResult.add(childrenResult);
            });
            log.debug("Converted user-defined data from [{}] to [{}], input-type: REPEATER",
                value, repeaterResult);
            result.put(itemName, repeaterResult);
            return;
        } else if (item.getType() == InputType.CHECKBOX) {
            ObjectMapper mapper = JsonUtils.createDefaultJsonMapper();
            TypeReference<List<Object>> typeRef = new TypeReference<>() {};

            List<Object> checkboxValue = null;
            try {
                checkboxValue = mapper.readValue(value.toString(), typeRef);
            } catch (JsonProcessingException e) {
                log.error("Converted user-defined data value [{}] List deserialization failed",
                    value, e);
                throw new RuntimeException(e);
            }
            Set<Object> checkboxResult = checkboxValue.stream()
                .map(val -> item.getDataType().convertTo(val))
                .collect(Collectors.toSet());
            log.debug("Converted pre-defined data from [{}] to [{}], input-type: CHECKBOX",
                value, checkboxResult);
            result.put(itemName, checkboxResult);
            return;
        }
        Object convertedValue = item.getDataType().convertTo(value);
        log.debug("Converted user-defined data from [{}] to [{}], type: [{}]",
            value, convertedValue, item.getDataType());
        result.put(itemName, convertedValue);
    }

    /**
     * Gets config item set.
     *
     * @param themeId theme id must not be blank
     * @return config item set
     */
    private Set<Item> getConfigItem(@NonNull String themeId) {
        // Get theme configuration
        List<Group> groups = themeService.fetchConfig(themeId);

        // Mix all items
        Set<Item> items = new LinkedHashSet<>();
        groups.forEach(group -> items.addAll(group.getItems()));

        // Convert to item map(key: item name, value: item)
        return items;
    }

    /**
     * Asserts theme id has text.
     *
     * @param themeId theme id to be checked
     */
    private void assertThemeIdHasText(String themeId) {
        Assert.hasText(themeId, "Theme id must not be null");
    }

}
