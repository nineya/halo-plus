package com.nineya.haloplus.model.vo;

import java.util.List;
import java.util.Map;

import com.nineya.haloplus.model.dto.CategoryDTO;
import com.nineya.haloplus.model.dto.TagDTO;
import com.nineya.haloplus.model.dto.post.BasePostSimpleDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Post list vo.
 *
 * @author johnniang
 * @author guqing
 * @author ryanwang
 * @date 2019-03-19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostListVO extends BasePostSimpleDTO {

    private Long commentCount;

    private List<TagDTO> tags;

    private List<CategoryDTO> categories;

    private Map<String, Object> metas;
}
