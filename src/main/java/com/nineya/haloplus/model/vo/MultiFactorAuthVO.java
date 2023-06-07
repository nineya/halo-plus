package com.nineya.haloplus.model.vo;

import com.nineya.haloplus.model.enums.MFAType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * MultiFactorAuth VO.
 *
 * @author Mu_Wooo
 * @date 2020-03-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MultiFactorAuthVO {

    private String qrImage;

    private String optAuthUrl;

    private String mfaKey;

    private MFAType mfaType;

    public MultiFactorAuthVO(MFAType mfaType) {
        this.mfaType = mfaType;
    }
}
