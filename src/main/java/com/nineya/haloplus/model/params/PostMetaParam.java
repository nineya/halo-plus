package com.nineya.haloplus.model.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.nineya.haloplus.model.entity.PostMeta;

/**
 * Post meta param.
 *
 * @author ryanwang
 * @author ikaisec
 * @date 2019-08-04
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PostMetaParam extends BaseMetaParam<PostMeta> {
}
