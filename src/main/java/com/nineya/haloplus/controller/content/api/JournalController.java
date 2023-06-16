package com.nineya.haloplus.controller.content.api;

import static org.springframework.data.domain.Sort.Direction.DESC;

import com.nineya.haloplus.cache.lock.CacheLock;
import com.nineya.haloplus.cache.lock.CacheParam;
import com.nineya.haloplus.model.dto.BaseCommentDTO;
import com.nineya.haloplus.model.dto.JournalWithCmtCountDTO;
import com.nineya.haloplus.model.entity.Journal;
import com.nineya.haloplus.model.entity.JournalComment;
import com.nineya.haloplus.model.enums.CommentStatus;
import com.nineya.haloplus.model.enums.JournalType;
import com.nineya.haloplus.model.params.JournalCommentParam;
import com.nineya.haloplus.model.vo.BaseCommentVO;
import com.nineya.haloplus.model.vo.BaseCommentWithParentVO;
import com.nineya.haloplus.model.vo.CommentWithHasChildrenVO;
import com.nineya.haloplus.service.JournalCommentService;
import com.nineya.haloplus.service.JournalService;
import com.nineya.haloplus.service.OptionService;
import com.nineya.haloplus.service.assembler.comment.JournalCommentRenderAssembler;
import io.swagger.annotations.ApiOperation;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

/**
 * Content journal controller.
 *
 * @author johnniang
 * @author ryanwang
 * @date 2019-04-26
 */
@RestController("ApiContentJournalController")
@RequestMapping("/api/content/journals")
public class JournalController {

    private final JournalService journalService;

    private final JournalCommentRenderAssembler journalCommentRenderAssembler;

    private final JournalCommentService journalCommentService;

    private final OptionService optionService;

    public JournalController(JournalService journalService,
        JournalCommentRenderAssembler journalCommentRenderAssembler,
        JournalCommentService journalCommentService,
        OptionService optionService) {
        this.journalService = journalService;
        this.journalCommentRenderAssembler = journalCommentRenderAssembler;
        this.journalCommentService = journalCommentService;
        this.optionService = optionService;
    }

    @GetMapping
    @ApiOperation("Lists journals")
    public Page<JournalWithCmtCountDTO> pageBy(
        @PageableDefault(sort = "createTime", direction = DESC) Pageable pageable) {
        Page<Journal> journals = journalService.pageBy(JournalType.PUBLIC, pageable);
        return journalService.convertToCmtCountDto(journals);
    }

    @GetMapping("{journalId:\\d+}")
    @ApiOperation("Gets a journal detail")
    public JournalWithCmtCountDTO getBy(@PathVariable("journalId") Integer journalId) {
        Journal journal = journalService.getById(journalId);
        return journalService.convertTo(journal);
    }

    @GetMapping("{journalId:\\d+}/comments/top_view")
    public Page<CommentWithHasChildrenVO> listTopComments(
        @PathVariable("journalId") Integer journalId,
        @RequestParam(name = "page", required = false, defaultValue = "0") int page,
        @SortDefault(sort = "createTime", direction = DESC) Sort sort) {
        Page<CommentWithHasChildrenVO> comments =
            journalCommentService.pageTopCommentsBy(journalId, CommentStatus.PUBLISHED,
                PageRequest.of(page, optionService.getCommentPageSize(), sort));
        comments.forEach(journalCommentRenderAssembler::clearSensitiveField);
        return comments;
    }

    @GetMapping("{journalId:\\d+}/comments/{commentParentId:\\d+}/children")
    public List<BaseCommentDTO> listChildrenBy(@PathVariable("journalId") Integer journalId,
        @PathVariable("commentParentId") Long commentParentId,
        @SortDefault(sort = "createTime", direction = DESC) Sort sort) {
        // Find all children comments
        List<JournalComment> postComments = journalCommentService
            .listChildrenBy(journalId, commentParentId, CommentStatus.PUBLISHED, sort);
        // Convert to base comment dto
        return journalCommentRenderAssembler.convertTo(postComments);
    }

    @GetMapping("{journalId:\\d+}/comments/tree_view")
    @ApiOperation("Lists comments with tree view")
    public Page<BaseCommentVO> listCommentsTree(@PathVariable("journalId") Integer journalId,
        @RequestParam(name = "page", required = false, defaultValue = "0") int page,
        @SortDefault(sort = "createTime", direction = DESC) Sort sort) {
        Page<BaseCommentVO> comments = journalCommentService
            .pageVosBy(journalId, PageRequest.of(page, optionService.getCommentPageSize(), sort));
        comments.getContent().forEach(journalCommentRenderAssembler::clearSensitiveField);
        return comments;
    }

    @GetMapping("{journalId:\\d+}/comments/list_view")
    @ApiOperation("Lists comment with list view")
    public Page<BaseCommentWithParentVO> listComments(@PathVariable("journalId") Integer journalId,
        @RequestParam(name = "page", required = false, defaultValue = "0") int page,
        @SortDefault(sort = "createTime", direction = DESC) Sort sort) {
        Page<BaseCommentWithParentVO> comments =
            journalCommentService.pageWithParentVoBy(journalId,
                PageRequest.of(page, optionService.getCommentPageSize(), sort));
        comments.getContent().forEach(journalCommentRenderAssembler::clearSensitiveField);
        return comments;
    }

    @PostMapping("comments")
    @ApiOperation("Comments a post")
    @CacheLock(autoDelete = false, traceRequest = true)
    public BaseCommentDTO comment(@RequestBody JournalCommentParam journalCommentParam) {

        // Escape content
        journalCommentParam.setContent(HtmlUtils
            .htmlEscape(journalCommentParam.getContent(), StandardCharsets.UTF_8.displayName()));
        return journalCommentRenderAssembler.convertTo(
            journalCommentService.createBy(journalCommentParam));
    }

    @PostMapping("{id:\\d+}/likes")
    @ApiOperation("Likes a journal")
    @CacheLock(autoDelete = false, traceRequest = true)
    public void like(@PathVariable("id") @CacheParam Integer id) {
        journalService.increaseLike(id);
    }
}
