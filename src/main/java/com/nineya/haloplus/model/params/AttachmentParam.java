package com.nineya.haloplus.model.params;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import com.nineya.haloplus.model.dto.base.InputConverter;
import com.nineya.haloplus.model.entity.Attachment;

/**
 * Attachment params.
 *
 * @author ryanwang
 * @date 2019/04/20
 */
@Data
public class AttachmentParam implements InputConverter<Attachment> {

    private Integer id;

    @Size(max = 255, message = "附件名称的字符长度不能超过 {max}")
    private String name;

    private String team;
}
