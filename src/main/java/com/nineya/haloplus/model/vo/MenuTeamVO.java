package com.nineya.haloplus.model.vo;

import java.util.List;

import com.nineya.haloplus.model.dto.MenuDTO;
import lombok.Data;
import lombok.ToString;

/**
 * Menu team vo.
 *
 * @author ryanwang
 * @date 2019/8/28
 */
@Data
@ToString
public class MenuTeamVO {

    private String team;

    private List<MenuDTO> menus;
}
