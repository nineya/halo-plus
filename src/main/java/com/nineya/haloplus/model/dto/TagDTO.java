package com.nineya.haloplus.model.dto;

import java.util.Date;

import com.nineya.haloplus.model.dto.base.OutputConverter;
import com.nineya.haloplus.model.entity.Tag;
import com.nineya.haloplus.model.support.HaloConst;
import lombok.Data;

/**
 * Tag output dto.
 *
 * @author johnniang
 * @author ryanwang
 * @date 2019-03-19
 */
@Data
public class TagDTO implements OutputConverter<TagDTO, Tag> {

    private Integer id;

    private String name;

    private String slug;

    private String color = HaloConst.DEFAULT_TAG_COLOR;

    private String thumbnail;

    private Date createTime;

    private String fullPath;
}
