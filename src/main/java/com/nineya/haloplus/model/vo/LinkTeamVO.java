package com.nineya.haloplus.model.vo;

import java.util.List;

import com.nineya.haloplus.model.dto.LinkDTO;
import lombok.Data;
import lombok.ToString;

/**
 * Link team vo.
 *
 * @author ryanwang
 * @date 2019/3/22
 */
@Data
@ToString
public class LinkTeamVO {

    private String team;

    private List<LinkDTO> links;
}
