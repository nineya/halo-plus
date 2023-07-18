package com.nineya.haloplus.controller.content.model;

import com.nineya.haloplus.service.OptionService;
import com.nineya.haloplus.service.ThemeService;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 * @author ryanwang
 * @date 2020-03-04
 */
@Component
public class LinkModel {

    private final ThemeService themeService;

    private final OptionService optionService;

    public LinkModel(ThemeService themeService,
        OptionService optionService) {
        this.themeService = themeService;
        this.optionService = optionService;
    }

    public String list(Model model) {
        model.addAttribute("is_links", true);
        model.addAttribute("links_title", optionService.getLinksTitle());
        model.addAttribute("meta_keywords", optionService.getSeoKeywords());
        model.addAttribute("meta_description", optionService.getSeoDescription());
        return themeService.render("links");
    }
}
