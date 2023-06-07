package com.nineya.haloplus.service.impl;

import com.nineya.haloplus.service.assembler.comment.JournalCommentAssembler;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import com.nineya.haloplus.exception.NotFoundException;
import com.nineya.haloplus.model.entity.JournalComment;
import com.nineya.haloplus.repository.JournalCommentRepository;
import com.nineya.haloplus.repository.JournalRepository;
import com.nineya.haloplus.service.JournalCommentService;
import com.nineya.haloplus.service.OptionService;
import com.nineya.haloplus.service.UserService;

/**
 * Journal comment service implementation.
 *
 * @author johnniang
 * @author guqing
 * @date 2019-04-25
 */
@Service
public class JournalCommentServiceImpl extends BaseCommentServiceImpl<JournalComment>
    implements JournalCommentService {

    private final JournalRepository journalRepository;

    public JournalCommentServiceImpl(JournalCommentRepository journalCommentRepository,
        OptionService optionService,
        UserService userService,
        ApplicationEventPublisher eventPublisher,
        JournalRepository journalRepository,
        JournalCommentAssembler journalCommentAssembler) {
        super(journalCommentRepository, optionService, userService, eventPublisher,
            journalCommentAssembler);
        this.journalRepository = journalRepository;
    }

    @Override
    public void validateTarget(@NonNull Integer journalId) {
        if (!journalRepository.existsById(journalId)) {
            throw new NotFoundException("查询不到该日志信息").setErrorData(journalId);
        }
    }
}
