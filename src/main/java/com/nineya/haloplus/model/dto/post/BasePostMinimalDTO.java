package com.nineya.haloplus.model.dto.post;

import java.util.Date;

import com.nineya.haloplus.model.entity.BasePost;
import com.nineya.haloplus.model.enums.PostEditorType;
import com.nineya.haloplus.model.enums.PostStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.nineya.haloplus.model.dto.base.OutputConverter;

/**
 * Base post minimal output dto.
 *
 * @author johnniang
 * @author ryanwang
 * @date 2019-03-19
 */
@Data
@ToString
@EqualsAndHashCode
public class BasePostMinimalDTO implements OutputConverter<BasePostMinimalDTO, BasePost> {

    private Integer id;

    private String title;

    private PostStatus status;

    private String slug;

    private PostEditorType editorType;

    private Date updateTime;

    private Date createTime;

    private Date editTime;

    private String metaKeywords;

    private String metaDescription;

    private String fullPath;
}
