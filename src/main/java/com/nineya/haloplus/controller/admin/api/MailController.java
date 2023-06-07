package com.nineya.haloplus.controller.admin.api;

import com.nineya.haloplus.mail.MailService;
import com.nineya.haloplus.model.params.MailParam;
import com.nineya.haloplus.model.support.BaseResponse;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nineya.haloplus.annotation.DisableOnCondition;

/**
 * Mail controller.
 *
 * @author johnniang
 * @date 2019-05-07
 */
@RestController
@RequestMapping("/api/admin/mails")
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("test")
    @ApiOperation("Tests the SMTP service")
    @DisableOnCondition
    public BaseResponse<String> testMail(@Valid @RequestBody MailParam mailParam) {
        mailService.testConnection();
        mailService.sendTextMail(mailParam.getTo(), mailParam.getSubject(), mailParam.getContent());
        return BaseResponse.ok("已发送，请查收。若确认没有收到邮件，请检查服务器日志");
    }

    @PostMapping("test/connection")
    @ApiOperation("Test connection with email server")
    @DisableOnCondition
    public BaseResponse<String> testConnection() {
        mailService.testConnection();
        return BaseResponse.ok("您和邮箱服务器的连接通畅");
    }

}
