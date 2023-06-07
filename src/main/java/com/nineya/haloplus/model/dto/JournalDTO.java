package com.nineya.haloplus.model.dto;

import java.util.Date;

import com.nineya.haloplus.model.dto.base.OutputConverter;
import com.nineya.haloplus.model.entity.Journal;
import com.nineya.haloplus.model.enums.JournalType;
import lombok.Data;

/**
 * Journal dto.
 *
 * @author johnniang
 * @author ryanwang
 * @date 2019-04-24
 */
@Data
public class JournalDTO implements OutputConverter<JournalDTO, Journal> {

    private Integer id;

    private String sourceContent;

    private String content;

    private Long likes;

    private Date createTime;

    private JournalType type;
}
