package com.nineya.haloplus.model.params;

import javax.validation.constraints.NotBlank;

import com.nineya.haloplus.model.support.CreateCheck;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Install parameters.
 *
 * @author johnniang
 * @date 3/19/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InstallParam extends UserParam {

    /**
     * Blog locale.
     */
    private String locale = "zh";

    /**
     * Blog title.
     */
    @NotBlank(message = "博客名称不能为空", groups = CreateCheck.class)
    private String title;

    /**
     * Blog url.
     */
    private String url;

}
