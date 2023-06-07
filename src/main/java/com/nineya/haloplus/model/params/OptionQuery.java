package com.nineya.haloplus.model.params;

import lombok.Data;
import com.nineya.haloplus.model.enums.OptionType;

/**
 * Option query params.
 *
 * @author ryanwang
 * @date 2019-12-02
 */
@Data
public class OptionQuery {

    private String keyword;

    private OptionType type;
}
