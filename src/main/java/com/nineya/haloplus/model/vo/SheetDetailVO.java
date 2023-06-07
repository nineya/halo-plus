package com.nineya.haloplus.model.vo;

import java.util.List;
import java.util.Set;

import com.nineya.haloplus.model.dto.BaseMetaDTO;
import com.nineya.haloplus.model.dto.post.BasePostDetailDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Sheet detail VO.
 *
 * @author ryanwang
 * @date 2019-12-10
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SheetDetailVO extends BasePostDetailDTO {

    private Set<Long> metaIds;

    private List<BaseMetaDTO> metas;
}
