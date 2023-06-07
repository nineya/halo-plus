package com.nineya.haloplus.service.impl;

import com.nineya.haloplus.service.assembler.comment.SheetCommentAssembler;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import com.nineya.haloplus.exception.BadRequestException;
import com.nineya.haloplus.exception.NotFoundException;
import com.nineya.haloplus.model.entity.Sheet;
import com.nineya.haloplus.model.entity.SheetComment;
import com.nineya.haloplus.repository.SheetCommentRepository;
import com.nineya.haloplus.repository.SheetRepository;
import com.nineya.haloplus.service.OptionService;
import com.nineya.haloplus.service.SheetCommentService;
import com.nineya.haloplus.service.UserService;

/**
 * Sheet comment service implementation.
 *
 * @author johnniang
 * @author ryanwang
 * @date 2019-04-24
 */
@Service
public class SheetCommentServiceImpl extends BaseCommentServiceImpl<SheetComment>
    implements SheetCommentService {

    private final SheetRepository sheetRepository;

    public SheetCommentServiceImpl(SheetCommentRepository sheetCommentRepository,
        OptionService optionService,
        UserService userService,
        ApplicationEventPublisher eventPublisher,
        SheetRepository sheetRepository,
        SheetCommentAssembler sheetCommentAssembler) {
        super(sheetCommentRepository, optionService, userService, eventPublisher,
            sheetCommentAssembler);
        this.sheetRepository = sheetRepository;
    }

    @Override
    public void validateTarget(@NonNull Integer sheetId) {
        Sheet sheet = sheetRepository.findById(sheetId)
            .orElseThrow(() -> new NotFoundException("查询不到该页面的信息").setErrorData(sheetId));

        if (sheet.getDisallowComment()) {
            throw new BadRequestException("该页面已被禁止评论").setErrorData(sheetId);
        }
    }
}
