package com.nineya.haloplus.listener.comment;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import com.nineya.haloplus.event.comment.CommentNewEvent;
import com.nineya.haloplus.event.comment.CommentReplyEvent;
import com.nineya.haloplus.exception.ServiceException;
import com.nineya.haloplus.mail.MailService;
import com.nineya.haloplus.model.dto.post.BasePostMinimalDTO;
import com.nineya.haloplus.model.entity.Journal;
import com.nineya.haloplus.model.entity.JournalComment;
import com.nineya.haloplus.model.entity.PostComment;
import com.nineya.haloplus.model.entity.SheetComment;
import com.nineya.haloplus.model.entity.User;
import com.nineya.haloplus.model.enums.CommentStatus;
import com.nineya.haloplus.model.properties.CommentProperties;
import com.nineya.haloplus.service.JournalCommentService;
import com.nineya.haloplus.service.JournalService;
import com.nineya.haloplus.service.OptionService;
import com.nineya.haloplus.service.PostCommentService;
import com.nineya.haloplus.service.PostService;
import com.nineya.haloplus.service.SheetCommentService;
import com.nineya.haloplus.service.SheetService;
import com.nineya.haloplus.service.ThemeService;
import com.nineya.haloplus.service.UserService;
import com.nineya.haloplus.service.assembler.PostAssembler;
import com.nineya.haloplus.service.assembler.SheetAssembler;
import com.nineya.haloplus.utils.ValidationUtils;

/**
 * PostComment event listener.
 *
 * @author johnniang
 * @author ryanwang
 * @date 2019-04-23
 */
@Slf4j
@Component
public class CommentEventListener {

    private final MailService mailService;

    private final OptionService optionService;

    private final PostCommentService postCommentService;

    private final SheetCommentService sheetCommentService;

    private final JournalCommentService journalCommentService;

    private final PostService postService;

    private final PostAssembler postAssembler;

    private final SheetService sheetService;

    private final SheetAssembler sheetAssembler;

    private final JournalService journalService;

    private final UserService userService;

    private final ThemeService themeService;

    public CommentEventListener(MailService mailService, OptionService optionService,
        PostCommentService postCommentService, SheetCommentService sheetCommentService,
        JournalCommentService journalCommentService, PostService postService,
        PostAssembler postAssembler, SheetService sheetService,
        SheetAssembler sheetAssembler, JournalService journalService,
        UserService userService,
        ThemeService themeService) {
        this.mailService = mailService;
        this.optionService = optionService;
        this.postCommentService = postCommentService;
        this.sheetCommentService = sheetCommentService;
        this.journalCommentService = journalCommentService;
        this.postService = postService;
        this.postAssembler = postAssembler;
        this.sheetService = sheetService;
        this.sheetAssembler = sheetAssembler;
        this.journalService = journalService;
        this.userService = userService;
        this.themeService = themeService;
    }

    /**
     * Received a new comment event.
     *
     * @param newEvent new comment event.
     */
    @Async
    @TransactionalEventListener
    public void handleCommentNewEvent(CommentNewEvent newEvent) {
        Boolean newCommentNotice = optionService
            .getByPropertyOrDefault(CommentProperties.NEW_NOTICE, Boolean.class, false);

        if (!newCommentNotice) {
            // Skip mailing
            return;
        }

        User user =
            userService.getCurrentUser().orElseThrow(() -> new ServiceException("未查询到博主信息"));

        Map<String, Object> data = new HashMap<>();

        StringBuilder subject = new StringBuilder();

        Boolean enabledAbsolutePath = optionService.isEnabledAbsolutePath();

        if (newEvent.getSource() instanceof PostCommentService) {
            // Get postComment id
            PostComment postComment = postCommentService.getById(newEvent.getCommentId());

            log.debug("Got post comment: [{}]", postComment);

            BasePostMinimalDTO post =
                postAssembler.convertToMinimal(postService.getById(postComment.getPostId()));

            data.put("pageFullPath", enabledAbsolutePath ? post.getFullPath() :
                optionService.getBlogBaseUrl() + post.getFullPath());
            data.put("pageTitle", post.getTitle());
            data.put("author", postComment.getAuthor());
            data.put("content", postComment.getContent());
            data.put("email", postComment.getEmail());
            data.put("status", postComment.getStatus());
            data.put("createTime", postComment.getCreateTime());
            data.put("authorUrl", postComment.getAuthorUrl());

            subject.append("您的博客文章《")
                .append(post.getTitle())
                .append("》有了新的评论。");

        } else if (newEvent.getSource() instanceof SheetCommentService) {
            SheetComment sheetComment = sheetCommentService.getById(newEvent.getCommentId());

            log.debug("Got sheet comment: [{}]", sheetComment);

            BasePostMinimalDTO sheet =
                sheetAssembler.convertToMinimal(sheetService.getById(sheetComment.getPostId()));

            data.put("pageFullPath", enabledAbsolutePath ? sheet.getFullPath() :
                optionService.getBlogBaseUrl() + sheet.getFullPath());
            data.put("pageTitle", sheet.getTitle());
            data.put("author", sheetComment.getAuthor());
            data.put("content", sheetComment.getContent());
            data.put("email", sheetComment.getEmail());
            data.put("status", sheetComment.getStatus());
            data.put("createTime", sheetComment.getCreateTime());
            data.put("authorUrl", sheetComment.getAuthorUrl());

            subject.append("您的博客页面《")
                .append(sheet.getTitle())
                .append("》有了新的评论。");
        } else if (newEvent.getSource() instanceof JournalCommentService) {
            JournalComment journalComment = journalCommentService.getById(newEvent.getCommentId());

            log.debug("Got journal comment: [{}]", journalComment);

            Journal journal = journalService.getById(journalComment.getPostId());

            StringBuilder url = new StringBuilder(optionService.getBlogBaseUrl())
                .append("/")
                .append(optionService.getJournalsPrefix());
            data.put("pageFullPath", url);
            data.put("pageTitle", journal.getCreateTime());
            data.put("author", journalComment.getAuthor());
            data.put("content", journalComment.getContent());
            data.put("email", journalComment.getEmail());
            data.put("status", journalComment.getStatus());
            data.put("createTime", journalComment.getCreateTime());
            data.put("authorUrl", journalComment.getAuthorUrl());

            subject.append("您的博客日志有了新的评论");
        }

        String template = "common/mail_template/mail_notice.ftl";

        if (themeService.templateExists("mail_template/mail_notice.ftl")) {
            template = themeService.renderWithSuffix("mail_template/mail_notice");
        }

        mailService.sendTemplateMail(user.getEmail(), subject.toString(), data, template);
    }

    /**
     * Received a new reply comment event.
     *
     * @param replyEvent reply comment event.
     */
    @Async
    @TransactionalEventListener
    public void handleCommentReplyEvent(CommentReplyEvent replyEvent) {
        Boolean replyCommentNotice = optionService
            .getByPropertyOrDefault(CommentProperties.REPLY_NOTICE, Boolean.class, false);

        if (!replyCommentNotice) {
            // Skip mailing
            return;
        }

        String baseAuthorEmail = "";

        String blogTitle = optionService.getBlogTitle();

        Map<String, Object> data = new HashMap<>();

        StringBuilder subject = new StringBuilder();

        Boolean enabledAbsolutePath = optionService.isEnabledAbsolutePath();

        log.debug("replyEvent.getSource():" + replyEvent.getSource().toString());

        if (replyEvent.getSource() instanceof PostCommentService) {

            PostComment postComment = postCommentService.getById(replyEvent.getCommentId());

            PostComment baseComment = postCommentService.getById(postComment.getParentId());

            if (StringUtils.isEmpty(baseComment.getEmail())
                && !ValidationUtils.isEmail(baseComment.getEmail())) {
                return;
            }

            if (!baseComment.getAllowNotification()) {
                return;
            }

            // only send email with published comments
            if (!postComment.getStatus().equals(CommentStatus.PUBLISHED)) {
                return;
            }

            baseAuthorEmail = baseComment.getEmail();

            BasePostMinimalDTO post =
                postAssembler.convertToMinimal(postService.getById(postComment.getPostId()));

            data.put("pageFullPath", enabledAbsolutePath ? post.getFullPath() :
                optionService.getBlogBaseUrl() + post.getFullPath());
            data.put("pageTitle", post.getTitle());
            data.put("baseAuthor", baseComment.getAuthor());
            data.put("baseContent", baseComment.getContent());
            data.put("replyAuthor", postComment.getAuthor());
            data.put("replyContent", postComment.getContent());
            data.put("baseAuthorEmail", baseComment.getEmail());
            data.put("replyAuthorEmail", postComment.getEmail());
            data.put("createTime", postComment.getCreateTime());
            data.put("authorUrl", postComment.getAuthorUrl());

            subject.append("您在【")
                .append(blogTitle)
                .append("】评论的文章《")
                .append(post.getTitle())
                .append("》有了新的评论。");
        } else if (replyEvent.getSource() instanceof SheetCommentService) {

            SheetComment sheetComment = sheetCommentService.getById(replyEvent.getCommentId());

            SheetComment baseComment = sheetCommentService.getById(sheetComment.getParentId());

            if (StringUtils.isEmpty(baseComment.getEmail())
                && !ValidationUtils.isEmail(baseComment.getEmail())) {
                return;
            }

            if (!baseComment.getAllowNotification()) {
                return;
            }

            baseAuthorEmail = baseComment.getEmail();

            BasePostMinimalDTO sheet =
                sheetAssembler.convertToMinimal(sheetService.getById(sheetComment.getPostId()));

            data.put("pageFullPath", enabledAbsolutePath ? sheet.getFullPath() :
                optionService.getBlogBaseUrl() + sheet.getFullPath());
            data.put("pageTitle", sheet.getTitle());
            data.put("baseAuthor", baseComment.getAuthor());
            data.put("baseContent", baseComment.getContent());
            data.put("replyAuthor", sheetComment.getAuthor());
            data.put("replyContent", sheetComment.getContent());
            data.put("baseAuthorEmail", baseComment.getEmail());
            data.put("replyAuthorEmail", sheetComment.getEmail());
            data.put("createTime", sheetComment.getCreateTime());
            data.put("authorUrl", sheetComment.getAuthorUrl());

            subject.append("您在【")
                .append(blogTitle)
                .append("】评论的页面《")
                .append(sheet.getTitle())
                .append("》有了新的评论。");
        } else if (replyEvent.getSource() instanceof JournalCommentService) {
            JournalComment journalComment =
                journalCommentService.getById(replyEvent.getCommentId());

            JournalComment baseComment =
                journalCommentService.getById(journalComment.getParentId());

            if (StringUtils.isEmpty(baseComment.getEmail())
                && !ValidationUtils.isEmail(baseComment.getEmail())) {
                return;
            }

            if (!baseComment.getAllowNotification()) {
                return;
            }

            baseAuthorEmail = baseComment.getEmail();

            Journal journal = journalService.getById(journalComment.getPostId());

            StringBuilder url = new StringBuilder(optionService.getBlogBaseUrl())
                .append("/")
                .append(optionService.getJournalsPrefix());
            data.put("pageFullPath", url);
            data.put("pageTitle", journal.getContent());
            data.put("baseAuthor", baseComment.getAuthor());
            data.put("baseContent", baseComment.getContent());
            data.put("replyAuthor", journalComment.getAuthor());
            data.put("replyContent", journalComment.getContent());
            data.put("baseAuthorEmail", baseComment.getEmail());
            data.put("replyAuthorEmail", journalComment.getEmail());
            data.put("createTime", journalComment.getCreateTime());
            data.put("authorUrl", journalComment.getAuthorUrl());

            subject.append("您在【")
                .append(blogTitle)
                .append("】评论的日志")
                .append("有了新的评论。");
        }

        String template = "common/mail_template/mail_reply.ftl";

        if (themeService.templateExists("mail_template/mail_reply.ftl")) {
            template = themeService.renderWithSuffix("mail_template/mail_reply");
        }

        mailService.sendTemplateMail(baseAuthorEmail, subject.toString(), data, template);
    }
}
