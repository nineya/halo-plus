package com.nineya.haloplus.core.freemarker.tag;

import com.nineya.haloplus.model.entity.Post;
import com.nineya.haloplus.model.enums.PostStatus;
import com.nineya.haloplus.model.support.HaloConst;
import com.nineya.haloplus.service.PostCategoryService;
import com.nineya.haloplus.service.PostService;
import com.nineya.haloplus.service.PostTagService;
import com.nineya.haloplus.service.assembler.PostRenderAssembler;
import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 * Freemarker custom tag of post.
 *
 * @author ryanwang
 * @date 2018-04-26
 */
@Component
public class PostTagDirective implements TemplateDirectiveModel {

    private final PostService postService;

    private final PostRenderAssembler postRenderAssembler;

    private final PostTagService postTagService;

    private final PostCategoryService postCategoryService;

    public PostTagDirective(Configuration configuration,
        PostService postService,
        PostRenderAssembler postRenderAssembler,
        PostTagService postTagService,
        PostCategoryService postCategoryService) {
        this.postService = postService;
        this.postRenderAssembler = postRenderAssembler;
        this.postTagService = postTagService;
        this.postCategoryService = postCategoryService;
        configuration.setSharedVariable("postTag", this);
    }

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
        TemplateDirectiveBody body) throws TemplateException, IOException {
        final DefaultObjectWrapperBuilder builder =
            new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
        if (params.containsKey(HaloConst.METHOD_KEY)) {
            String method = params.get(HaloConst.METHOD_KEY).toString();
            switch (method) {
                case "latest":
                    int top = Integer.parseInt(params.get("top").toString());
                    env.setVariable("posts", builder.build()
                        .wrap(postRenderAssembler.convertToListVo(postService.listLatest(top))));
                    break;
                case "sort":
                    int count = Integer.parseInt(params.get("count").toString());
                    String sort = params.get("sort").toString();
                    Sort.Direction direction = Sort.Direction.fromOptionalString(
                        params.getOrDefault("direction", "ASC").toString())
                        .orElse(Sort.Direction.ASC);
                    Pageable pageable = PageRequest.of(0, count, direction, sort);
                    env.setVariable("posts", builder.build()
                        .wrap(postRenderAssembler.convertToListVo(
                            postService.pageBy(PostStatus.PUBLISHED, pageable).getContent())));
                    break;
                case "count":
                    env.setVariable("count",
                        builder.build().wrap(postService.countByStatus(PostStatus.PUBLISHED)));
                    break;
                case "archiveYear":
                    env.setVariable("archives",
                        builder.build().wrap(postService.listYearArchives()));
                    break;
                case "archiveMonth":
                    env.setVariable("archives",
                        builder.build().wrap(postService.listMonthArchives()));
                    break;
                case "archive":
                    String type = params.get("type").toString();
                    env.setVariable("archives", builder.build().wrap(
                        "year".equals(type) ? postService.listYearArchives() :
                            postService.listMonthArchives()));
                    break;
                case "listByCategoryId":
                    Integer categoryId = Integer.parseInt(params.get("categoryId").toString());
                    env.setVariable("posts", builder.build()
                        .wrap(postRenderAssembler.convertToListVo(
                            postCategoryService.listPostBy(categoryId, PostStatus.PUBLISHED))));
                    break;
                case "listByCategorySlug":
                    String categorySlug = params.get("categorySlug").toString();
                    List<Post> posts =
                        postCategoryService.listPostBy(categorySlug, PostStatus.PUBLISHED);
                    env.setVariable("posts",
                        builder.build().wrap(postRenderAssembler.convertToListVo(posts)));
                    break;
                case "listByTagId":
                    Integer tagId = Integer.parseInt(params.get("tagId").toString());
                    env.setVariable("posts", builder.build().wrap(postRenderAssembler
                        .convertToListVo(postTagService.listPostsBy(tagId, PostStatus.PUBLISHED))));
                    break;
                case "listByTagSlug":
                    String tagSlug = params.get("tagSlug").toString();
                    env.setVariable("posts", builder.build()
                        .wrap(
                            postRenderAssembler.convertToListVo(
                                postTagService.listPostsBy(tagSlug, PostStatus.PUBLISHED)
                            )
                        )
                    );
                    break;
                default:
                    break;
            }
        }
        body.render(env.getOut());
    }

}
