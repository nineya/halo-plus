package com.nineya.haloplus.service;

import com.nineya.haloplus.service.base.BaseCommentService;
import com.nineya.haloplus.model.entity.PostComment;

/**
 * Post comment service interface.
 *
 * @author johnniang
 * @author ryanwang
 * @author guqing
 * @date 2019-03-14
 */
public interface PostCommentService extends BaseCommentService<PostComment> {

    /**
     * Validate CommentBlackList Status.
     */
    void validateCommentBlackListStatus();
}
