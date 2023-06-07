package com.nineya.haloplus.model.dto;

import com.nineya.haloplus.model.dto.base.OutputConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Statistic with user info DTO.
 *
 * @author ryanwang
 * @date 2019-12-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StatisticWithUserDTO extends StatisticDTO
    implements OutputConverter<StatisticWithUserDTO, StatisticDTO> {

    private UserDTO user;
}
