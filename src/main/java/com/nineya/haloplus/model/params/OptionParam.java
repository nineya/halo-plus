package com.nineya.haloplus.model.params;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import com.nineya.haloplus.model.dto.base.InputConverter;
import com.nineya.haloplus.model.entity.Option;
import com.nineya.haloplus.model.enums.OptionType;

/**
 * Optional param.
 *
 * @author johnniang
 * @author ryanwang
 * @date 2019-03-20
 */
@Data
public class OptionParam implements InputConverter<Option> {

    @NotBlank(message = "Option key must not be blank")
    @Size(max = 100, message = "Length of option key must not be more than {max}")
    private String key;

    private String value;

    private OptionType type;
}
