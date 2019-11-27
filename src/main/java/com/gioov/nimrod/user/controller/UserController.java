package com.gioov.nimrod.user.controller;

import com.gioov.nimrod.common.others.Common;
import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.user.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.gioov.nimrod.common.security.SimpleUserDetailsServiceImpl.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Controller
@RequestMapping(User.Page.USER)
public class UserController {
    @OperationLog("登录页")
    @RequestMapping("/login")
    public String login() {
        return Common.trimSlash(User.Page.LOGIN);
    }

    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/USER/LIST')")
    @RequestMapping("/list")
    public String user() {
        return Common.trimSlash(User.Page.USER + "/list");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/add_dialog")
    public String addDialog() {
        return Common.trimSlash(User.Page.USER + "/add_dialog");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/edit_dialog")
    public String editDialog() {
        return Common.trimSlash(User.Page.USER + "/edit_dialog");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/change_avatar_dialog")
    public String changeAvatarDialog() {
        return Common.trimSlash(User.Page.USER + "/change_avatar_dialog");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/change_email_dialog")
    public String changeEmailDialog() {
        return Common.trimSlash(User.Page.USER + "/change_email_dialog");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/change_password_dialog")
    public String changePasswordDialog() {
        return Common.trimSlash(User.Page.USER + "/change_password_dialog");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/profile")
    public String profile() {
        return Common.trimSlash(User.Page.USER + "/profile");
    }


}