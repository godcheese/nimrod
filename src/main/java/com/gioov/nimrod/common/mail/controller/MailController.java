package com.gioov.nimrod.common.mail.controller;

import com.gioov.nimrod.common.Common;
import com.gioov.nimrod.common.constant.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Controller
@RequestMapping(Page.System.MAIL)
public class MailController {

    /**
     * 邮件队列 页面
     *
     * @return String
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/SYSTEM/MAIL/PAGE_ALL')")
    @RequestMapping("/page_all")
    public String pageAllQueue() {
        return Common.filterStartSlash(Page.System.MAIL + "/page_all");
    }

    /**
     * 发送邮件 页面
     *
     * @return String
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/SYSTEM/MAIL/SEND')")
    @RequestMapping("/send")
    public String send() {
        return Common.filterStartSlash(Page.System.MAIL + "/send");
    }

    /**
     * 编辑重发 对话框
     *
     * @return String
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/edit_and_send_dialog")
    public String editAndSendDialog() {
        return Common.filterStartSlash(Page.System.MAIL + "/edit_and_send_dialog");
    }

}
