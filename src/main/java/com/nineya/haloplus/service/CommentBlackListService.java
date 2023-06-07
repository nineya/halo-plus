package com.nineya.haloplus.service;

import com.nineya.haloplus.service.base.CrudService;
import com.nineya.haloplus.model.entity.CommentBlackList;
import com.nineya.haloplus.model.enums.CommentViolationTypeEnum;

/**
 * Comment BlackList Service
 *
 * @author Lei XinXin
 * @date 2020/1/3
 */
public interface CommentBlackListService extends CrudService<CommentBlackList, Long> {
    /**
     * 评论封禁状态
     *
     * @param ipAddress ip地址
     * @return boolean
     */
    CommentViolationTypeEnum commentsBanStatus(String ipAddress);
}
