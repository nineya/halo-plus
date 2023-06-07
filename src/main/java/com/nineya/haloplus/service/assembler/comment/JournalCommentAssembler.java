package com.nineya.haloplus.service.assembler.comment;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import com.nineya.haloplus.model.dto.JournalDTO;
import com.nineya.haloplus.model.entity.Journal;
import com.nineya.haloplus.model.entity.JournalComment;
import com.nineya.haloplus.model.vo.JournalCommentWithJournalVO;
import com.nineya.haloplus.repository.JournalRepository;
import com.nineya.haloplus.service.OptionService;
import com.nineya.haloplus.utils.ServiceUtils;

/**
 * Journal comment assembler.
 *
 * @author guqing
 * @date 2022-03-08
 */
@Component
public class JournalCommentAssembler extends BaseCommentAssembler<JournalComment> {

    private final JournalRepository journalRepository;

    public JournalCommentAssembler(OptionService optionService,
        JournalRepository journalRepository) {
        super(optionService);
        this.journalRepository = journalRepository;
    }

    @NonNull
    public List<JournalCommentWithJournalVO> convertToWithJournalVo(
        List<JournalComment> journalComments) {

        if (CollectionUtils.isEmpty(journalComments)) {
            return Collections.emptyList();
        }

        Set<Integer> journalIds =
            ServiceUtils.fetchProperty(journalComments, JournalComment::getPostId);

        // Get all journals
        List<Journal> journals = journalRepository.findAllById(journalIds);

        Map<Integer, Journal> journalMap = ServiceUtils.convertToMap(journals, Journal::getId);

        return journalComments.stream()
            .filter(journalComment -> journalMap.containsKey(journalComment.getPostId()))
            .map(journalComment -> {
                JournalCommentWithJournalVO journalCmtWithJournalVo =
                    new JournalCommentWithJournalVO().convertFrom(journalComment);
                journalCmtWithJournalVo.setJournal(
                    new JournalDTO().convertFrom(journalMap.get(journalComment.getPostId())));
                journalCmtWithJournalVo.setAvatar(buildAvatarUrl(journalComment.getGravatarMd5()));
                return journalCmtWithJournalVo;
            })
            .collect(Collectors.toList());
    }

    @NonNull
    public Page<JournalCommentWithJournalVO> convertToWithJournalVo(
        @NonNull Page<JournalComment> journalCommentPage) {
        Assert.notNull(journalCommentPage, "Journal comment page must not be null");

        // Convert the list
        List<JournalCommentWithJournalVO> journalCmtWithJournalVos =
            convertToWithJournalVo(journalCommentPage.getContent());

        // Build and return
        return new PageImpl<>(journalCmtWithJournalVos, journalCommentPage.getPageable(),
            journalCommentPage.getTotalElements());
    }
}
