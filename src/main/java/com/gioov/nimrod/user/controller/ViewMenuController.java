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
@RequestMapping(User.Page.VIEW_MENU)
public class ViewMenuController {

    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/USER/VIEW_MENU/PAGE_ALL')")
    @RequestMapping("/page_all")
    public String pageAll() {
        return Common.trimSlash(User.Page.VIEW_MENU + "/page_all");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/add_dialog")
    public String addDialog() {
        return Common.trimSlash(User.Page.VIEW_MENU + "/add_dialog");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/edit_dialog")
    public String editDialog() {
        return Common.trimSlash(User.Page.VIEW_MENU + "/edit_dialog");
    }

}
