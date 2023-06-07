package com.nineya.haloplus.controller.content;

import static org.springframework.data.domain.Sort.Direction.DESC;

import com.nineya.haloplus.model.entity.Post;
import com.nineya.haloplus.model.vo.PostListVO;
import com.nineya.haloplus.service.OptionService;
import com.nineya.haloplus.service.PostService;
import com.nineya.haloplus.service.ThemeService;
import com.nineya.haloplus.service.assembler.PostRenderAssembler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

/**
 * Search controller.
 *
 * @author ryanwang
 * @date 2019-04-21
 */
@Controller
@RequestMapping(value = "/search")
public class ContentSearchController {

    private final PostService postService;

    private final PostRenderAssembler postRenderAssembler;

    private final OptionService optionService;

    private final ThemeService themeService;

    public ContentSearchController(PostService postService,
        PostRenderAssembler postRenderAssembler, OptionService optionService,
        ThemeService themeService) {
        this.postService = postService;
        this.postRenderAssembler = postRenderAssembler;
        this.optionService = optionService;
        this.themeService = themeService;
    }

    /**
     * Render post search page.
     *
     * @param model model
     * @param keyword keyword
     * @return template path : themes/{theme}/search.ftl
     */
    @GetMapping
    public String search(Model model,
        @RequestParam(value = "keyword") String keyword) {
        return this.search(model, HtmlUtils.htmlEscape(keyword), 1, Sort.by(DESC, "createTime"));
    }

    /**
     * Render post search page.
     *
     * @param model model
     * @param keyword keyword
     * @return template path :themes/{theme}/search.ftl
     */
    @GetMapping(value = "page/{page}")
    public String search(Model model,
        @RequestParam(value = "keyword") String keyword,
        @PathVariable(value = "page") Integer page,
        @SortDefault(sort = "createTime", direction = DESC) Sort sort) {
        final Pageable pageable = PageRequest.of(page - 1, optionService.getPostPageSize(), sort);
        final Page<Post> postPage = postService.pageBy(keyword, pageable);

        final Page<PostListVO> posts = postRenderAssembler.convertToListVo(postPage);

        model.addAttribute("is_search", true);
        model.addAttribute("keyword", HtmlUtils.htmlEscape(keyword));
        model.addAttribute("posts", posts);
        model.addAttribute("meta_keywords", optionService.getSeoKeywords());
        model.addAttribute("meta_description", optionService.getSeoDescription());
        return themeService.render("search");
    }
}
