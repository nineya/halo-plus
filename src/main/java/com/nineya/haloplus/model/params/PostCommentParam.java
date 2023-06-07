package com.nineya.haloplus.model.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.nineya.haloplus.model.entity.PostComment;

/**
 * PostComment param.
 *
 * @author johnniang
 * @date 3/22/19
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PostCommentParam extends BaseCommentParam<PostComment> {

}
