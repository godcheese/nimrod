package com.gioov.nimrod.mail.controller;

import com.gioov.nimrod.common.others.Common;
import com.gioov.nimrod.mail.Mail;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.gioov.nimrod.common.security.SimpleUserDetailsServiceImpl.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Controller
@RequestMapping(Mail.Page.MAIL)
public class MailController {

    /**
     * 邮件队列 页面
     * @return String
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/MAIL/LIST')")
    @RequestMapping("/list")
    public String list() {
        return Common.trimSlash(Mail.Page.MAIL + "/list");
    }

    /**
     * 发送邮件 页面
     * @return String
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/MAIL/SEND')")
    @RequestMapping("/send")
    public String send() {
        return Common.trimSlash(Mail.Page.MAIL  + "/send");
    }

    /**
     * 编辑重发 对话框
     * @return String
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/send_dialog")
    public String sendDialog() {
        return Common.trimSlash(Mail.Page.MAIL + "/send_dialog");
    }
    /**
     * 编辑重发 对话框
     * @return String
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/view_dialog")
    public String viewDialog() {
        return Common.trimSlash(Mail.Page.MAIL + "/view_dialog");
    }
}
