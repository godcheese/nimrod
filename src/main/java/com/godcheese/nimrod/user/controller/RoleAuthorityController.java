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
@RequestMapping(User.Page.ROLE_AUTHORITY)
public class RoleAuthorityController {
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/add_dialog")
    public String addDialog() {
        return Common.trimSlash(User.Page.ROLE_AUTHORITY + "/add_dialog");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/edit_dialog")
    public String editDialog() {
        return Common.trimSlash(User.Page.ROLE_AUTHORITY + "/edit_dialog");
    }

    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/USER/ROLE_AUTHORITY/VIEW_PAG')")
    @RequestMapping("/view_page")
    public String viewPage() {
        return Common.trimSlash(User.Page.ROLE_AUTHORITY + "/view_page");
    }

    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/USER/ROLE_AUTHORITY/API')")
    @RequestMapping("/api")
    public String api() {
        return Common.trimSlash(User.Page.ROLE_AUTHORITY + "/api");
    }

}
