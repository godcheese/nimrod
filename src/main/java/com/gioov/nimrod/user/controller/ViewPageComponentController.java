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
@RequestMapping(User.Page.VIEW_PAGE_COMPONENT)
public class ViewPageComponentController {
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/SYSTEM/VIEW_PAGE_COMPONENT/LIST')")
    @RequestMapping("/list")
    public String list() {
        return Common.trimSlash(User.Page.VIEW_PAGE_COMPONENT + "/list");
    }

    @RequestMapping("/add_dialog")
    public String addDialog() {
        return Common.trimSlash(User.Page.VIEW_PAGE_COMPONENT + "/add_dialog");
    }

    @RequestMapping("/edit_dialog")
    public String editDialog() {
        return Common.trimSlash(User.Page.VIEW_PAGE_COMPONENT + "/edit_dialog");
    }
}
