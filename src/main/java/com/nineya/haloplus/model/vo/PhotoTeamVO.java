package com.nineya.haloplus.model.vo;

import java.util.List;

import com.nineya.haloplus.model.dto.PhotoDTO;
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
public class PhotoTeamVO {

    private String team;

    private List<PhotoDTO> photos;
}
