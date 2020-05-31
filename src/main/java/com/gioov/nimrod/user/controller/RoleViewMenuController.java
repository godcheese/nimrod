package com.gioov.nimrod.user.controller;

import com.gioov.nimrod.common.others.Common;
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
@RequestMapping(User.Page.ROLE_VIEW_MENU)
public class RoleViewMenuController {
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/USER/ROLE_VIEW_MENU/LIST')")
    @RequestMapping("/list")
    public String list() {
        return Common.trimSlash(User.Page.ROLE_VIEW_MENU + "/list");
    }
}
