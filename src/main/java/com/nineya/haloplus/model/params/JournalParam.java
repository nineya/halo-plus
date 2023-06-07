package com.nineya.haloplus.model.params;

import java.util.Objects;
import javax.validation.constraints.NotBlank;

import com.nineya.haloplus.utils.MarkdownUtils;
import lombok.Data;
import com.nineya.haloplus.model.dto.base.InputConverter;
import com.nineya.haloplus.model.entity.Journal;
import com.nineya.haloplus.model.enums.JournalType;

/**
 * Journal param.
 *
 * @author johnniang
 * @author ryanwang
 * @date 2019-4-25
 */
@Data
public class JournalParam implements InputConverter<Journal> {

    @NotBlank(message = "内容不能为空")
    private String sourceContent;

    private String content;

    private JournalType type = JournalType.PUBLIC;

    /**
     * if {@code true}, it means is that do not let the back-end render the original content
     * because the content has been rendered, and you only need to store the original content.
     */
    private Boolean keepRaw = false;

    @Override
    public Journal convertTo() {
        Journal journal = InputConverter.super.convertTo();
        populateContent(journal);
        return journal;
    }

    @Override
    public void update(Journal domain) {
        InputConverter.super.update(domain);
        populateContent(domain);
    }

    private void populateContent(Journal journal) {
        if (Objects.equals(keepRaw, false)) {
            journal.setContent(MarkdownUtils.renderHtml(sourceContent));
        } else {
            journal.setContent(content);
        }
    }
}
