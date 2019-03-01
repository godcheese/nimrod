package com.gioov.nimrod.user.controller;

import com.gioov.nimrod.common.Common;
import com.gioov.nimrod.common.Url;
import com.gioov.nimrod.user.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Controller
@RequestMapping(User.Page.USER_ROLE)
public class UserRoleController {

    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/USER/USER_ROLE/PAGE_ALL')")
    @RequestMapping("/page_all")
    public String pageAll() {
        return Common.trimSlash(User.Page.USER_ROLE + "/page_all");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/add_dialog")
    public String addDialog() {
        return Common.trimSlash(User.Page.USER_ROLE + "/add_dialog");
    }

}
