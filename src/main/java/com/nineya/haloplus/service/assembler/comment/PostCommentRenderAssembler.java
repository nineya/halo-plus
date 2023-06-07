package com.nineya.haloplus.service.assembler.comment;

import java.util.Comparator;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import com.nineya.haloplus.model.dto.BaseCommentDTO;
import com.nineya.haloplus.model.entity.PostComment;
import com.nineya.haloplus.model.vo.BaseCommentVO;
import com.nineya.haloplus.model.vo.PostCommentWithPostVO;
import com.nineya.haloplus.repository.PostRepository;
import com.nineya.haloplus.service.OptionService;

/**
 * Post comment assembler for theme render.
 *
 * @author guqing
 * @date 2022-03-09
 */
@Component
public class PostCommentRenderAssembler extends PostCommentAssembler {

    public PostCommentRenderAssembler(OptionService optionService,
        PostRepository postRepository) {
        super(optionService, postRepository);
    }

    @NonNull
    @Override
    public BaseCommentDTO convertTo(@NonNull PostComment comment) {
        clearSensitiveField(comment);
        return super.convertTo(comment);
    }

    @NonNull
    @Override
    public List<BaseCommentDTO> convertTo(@NonNull List<PostComment> postComments) {
        postComments.forEach(this::clearSensitiveField);
        return super.convertTo(postComments);
    }

    @NonNull
    @Override
    public Page<BaseCommentDTO> convertTo(@NonNull Page<PostComment> postComments) {
        postComments.forEach(this::clearSensitiveField);
        return super.convertTo(postComments);
    }

    @Override
    public List<BaseCommentVO> convertToVo(List<PostComment> postComments,
        Comparator<BaseCommentVO> comparator) {
        if (!CollectionUtils.isEmpty(postComments)) {
            postComments.forEach(this::clearSensitiveField);
        }
        return super.convertToVo(postComments, comparator);
    }

    @NonNull
    @Override
    public Page<PostCommentWithPostVO> convertToWithPostVo(@NonNull Page<PostComment> commentPage) {
        commentPage.getContent().forEach(this::clearSensitiveField);
        return super.convertToWithPostVo(commentPage);
    }

    @NonNull
    @Override
    public PostCommentWithPostVO convertToWithPostVo(@NonNull PostComment comment) {
        this.clearSensitiveField(comment);
        return super.convertToWithPostVo(comment);
    }

    @NonNull
    @Override
    public List<PostCommentWithPostVO> convertToWithPostVo(List<PostComment> postComments) {
        if (!CollectionUtils.isEmpty(postComments)) {
            postComments.forEach(this::clearSensitiveField);
        }
        return super.convertToWithPostVo(postComments);
    }

    @NonNull
    @Override
    public Page<BaseCommentVO> pageVosBy(@NonNull List<PostComment> postComments,
        @NonNull Pageable pageable) {
        postComments.forEach(this::clearSensitiveField);
        return super.pageVosBy(postComments, pageable);
    }
}
