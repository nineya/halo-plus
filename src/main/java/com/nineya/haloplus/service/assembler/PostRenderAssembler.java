package com.nineya.haloplus.service.assembler;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import com.nineya.haloplus.model.entity.Content;
import com.nineya.haloplus.model.entity.Content.PatchedContent;
import com.nineya.haloplus.model.entity.Post;
import com.nineya.haloplus.model.vo.PostDetailVO;
import com.nineya.haloplus.service.CategoryService;
import com.nineya.haloplus.service.ContentPatchLogService;
import com.nineya.haloplus.service.ContentService;
import com.nineya.haloplus.service.OptionService;
import com.nineya.haloplus.service.PostCategoryService;
import com.nineya.haloplus.service.PostCommentService;
import com.nineya.haloplus.service.PostMetaService;
import com.nineya.haloplus.service.PostTagService;
import com.nineya.haloplus.service.TagService;

/**
 * Post assembler for theme render.
 *
 * @author guqing
 * @date 2022-03-01
 */
@Component
public class PostRenderAssembler extends PostAssembler {

    private final ContentService contentService;
    private final ContentPatchLogService contentPatchLogService;

    public PostRenderAssembler(ContentService contentService,
        OptionService optionService,
        PostTagService postTagService,
        PostCategoryService postCategoryService,
        PostMetaService postMetaService,
        PostCommentService postCommentService,
        TagService tagService,
        CategoryService categoryService,
        ContentPatchLogService contentPatchLogService) {
        super(contentService, optionService, postTagService, postCategoryService, postMetaService,
            postCommentService, tagService, categoryService);
        this.contentService = contentService;
        this.contentPatchLogService = contentPatchLogService;
    }

    @Override
    public Page<PostDetailVO> convertToDetailVo(Page<Post> postPage) {
        Assert.notNull(postPage, "Post page must not be null");
        // Populate post content
        postPage.getContent().forEach(post -> {
            Content postContent = contentService.getById(post.getId());
            post.setContent(Content.PatchedContent.of(postContent));
        });
        return postPage.map(this::convertToDetailVo);
    }

    @Override
    public PostDetailVO convertToDetailVo(Post post) {
        Assert.notNull(post, "The post must not be null.");
        post.setContent(PatchedContent.of(contentService.getById(post.getId())));
        return super.convertToDetailVo(post);
    }

    /**
     * Gets for preview.
     *
     * @param post post
     * @return post detail with latest patched content.
     */
    public PostDetailVO convertToPreviewDetailVo(Post post) {
        Assert.notNull(post, "The post must not be null.");
        post.setContent(getLatestContentBy(post.getId()));
        return super.convertToDetailVo(post);
    }

    private PatchedContent getLatestContentBy(Integer postId) {
        Content postContent = contentService.getById(postId);
        // Use the head pointer stored in the post content.
        return contentPatchLogService.getPatchedContentById(postContent.getHeadPatchLogId());
    }
}
