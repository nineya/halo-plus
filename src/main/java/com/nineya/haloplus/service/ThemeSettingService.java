package com.nineya.haloplus.service;

import java.util.List;
import java.util.Map;

import com.nineya.haloplus.service.base.CrudService;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import com.nineya.haloplus.model.entity.ThemeSetting;

/**
 * Theme setting service interface.
 *
 * @author johnniang
 * @date 2019-04-08
 */
public interface ThemeSettingService extends CrudService<ThemeSetting, Integer> {

    /**
     * Saves theme setting.
     *
     * @param key setting key must not be blank
     * @param value setting value
     * @param themeId theme id must not be blank
     * @return theme setting or null if the key does not exist
     */
    @Nullable
    @Transactional
    ThemeSetting save(@NonNull String key, @Nullable String value, @NonNull String themeId);

    /**
     * Saves theme settings.
     *
     * @param settings theme setting map
     * @param themeId theme id must not be blank
     */
    @Transactional
    void save(@Nullable Map<String, Object> settings, @NonNull String themeId);

    /**
     * Lists theme settings by theme id.
     *
     * @param themeId theme id must not be blank
     * @return a list of theme setting
     */
    @NonNull
    List<ThemeSetting> listBy(String themeId);

    /**
     * Lists theme settings as map.
     *
     * @param themeId theme id must not be blank
     * @return theme setting map
     */
    @NonNull
    Map<String, Object> listAsMapBy(@NonNull String themeId);

    /**
     * Lists theme settings as map by <code>themeId</code> and <code>group</code> name.
     *
     * @param themeId theme id must not be blank.
     * @param group theme group name must not be blank.
     * @return theme setting map(key: item name, value: item)
     */
    @NonNull
    Map<String, Object> listAsMapBy(String themeId, String group);

    /**
     * Delete unused theme setting.
     */
    void deleteInactivated();
}
