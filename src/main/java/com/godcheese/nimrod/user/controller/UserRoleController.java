package com.godcheese.nimrod.user.controller;

import com.godcheese.nimrod.common.others.Common;
import com.godcheese.nimrod.user.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.godcheese.nimrod.common.security.SimpleUserDetailsServiceImpl.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Controller
@RequestMapping(User.Page.USER_ROLE)
public class UserRoleController {
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/USER/USER_ROLE/LIST')")
    @RequestMapping("/list")
    public String userRole() {
        return Common.trimSlash(User.Page.USER_ROLE + "/list");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/add_dialog")
    public String addDialog() {
        return Common.trimSlash(User.Page.USER_ROLE + "/add_dialog");
    }
}
