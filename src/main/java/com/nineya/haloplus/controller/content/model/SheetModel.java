package com.nineya.haloplus.controller.content.model;

import java.util.List;

import com.nineya.haloplus.cache.AbstractStringCacheStore;
import com.nineya.haloplus.model.entity.Sheet;
import com.nineya.haloplus.model.entity.SheetMeta;
import com.nineya.haloplus.model.enums.PostStatus;
import com.nineya.haloplus.model.support.HaloConst;
import com.nineya.haloplus.model.vo.SheetDetailVO;
import com.nineya.haloplus.service.OptionService;
import com.nineya.haloplus.service.SheetMetaService;
import com.nineya.haloplus.service.SheetService;
import com.nineya.haloplus.service.ThemeService;
import com.nineya.haloplus.service.assembler.SheetRenderAssembler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import com.nineya.haloplus.exception.ForbiddenException;

/**
 * Sheet model.
 *
 * @author ryanwang
 * @date 2020-01-07
 */
@Component
public class SheetModel {

    private final SheetService sheetService;

    private final SheetRenderAssembler sheetRenderAssembler;

    private final SheetMetaService sheetMetaService;

    private final AbstractStringCacheStore cacheStore;

    private final ThemeService themeService;

    private final OptionService optionService;

    public SheetModel(SheetService sheetService,
        SheetRenderAssembler sheetRenderAssembler,
        SheetMetaService sheetMetaService,
        AbstractStringCacheStore cacheStore,
        ThemeService themeService,
        OptionService optionService) {
        this.sheetService = sheetService;
        this.sheetRenderAssembler = sheetRenderAssembler;
        this.sheetMetaService = sheetMetaService;
        this.cacheStore = cacheStore;
        this.themeService = themeService;
        this.optionService = optionService;
    }

    /**
     * Sheet content.
     *
     * @param sheet sheet
     * @param token token
     * @param model model
     * @return template name
     */
    public String content(Sheet sheet, String token, Model model) {
        SheetDetailVO sheetDetailVo;
        if (StringUtils.isEmpty(token)) {
            sheet = sheetService.getBy(PostStatus.PUBLISHED, sheet.getSlug());
            sheetDetailVo = sheetRenderAssembler.convertToDetailVo(sheet);
        } else {
            // verify token
            String cachedToken = cacheStore.getAny(token, String.class)
                .orElseThrow(() -> new ForbiddenException("您没有该页面的访问权限"));
            if (!cachedToken.equals(token)) {
                throw new ForbiddenException("您没有该页面的访问权限");
            }
            sheetDetailVo = sheetRenderAssembler.convertToPreviewDetailVo(sheet);
        }

        sheetService.publishVisitEvent(sheet.getId());

        List<SheetMeta> metas = sheetMetaService.listBy(sheet.getId());

        // Generate meta keywords.
        if (StringUtils.isNotEmpty(sheet.getMetaKeywords())) {
            model.addAttribute("meta_keywords", sheet.getMetaKeywords());
        } else {
            model.addAttribute("meta_keywords", optionService.getSeoKeywords());
        }

        // Generate meta description.
        if (StringUtils.isNotEmpty(sheet.getMetaDescription())) {
            model.addAttribute("meta_description", sheet.getMetaDescription());
        } else {
            model.addAttribute("meta_description",
                sheetService.generateDescription(sheet.getContent().getContent()));
        }

        // sheet and post all can use
        model.addAttribute("sheet", sheetDetailVo);
        model.addAttribute("post", sheetDetailVo);
        model.addAttribute("is_sheet", true);
        model.addAttribute("metas", sheetMetaService.convertToMap(metas));

        if (themeService.templateExists(
            ThemeService.CUSTOM_SHEET_PREFIX + sheet.getTemplate() + HaloConst.SUFFIX_FTL)) {
            return themeService.render(ThemeService.CUSTOM_SHEET_PREFIX + sheet.getTemplate());
        }
        return themeService.render("sheet");
    }
}
