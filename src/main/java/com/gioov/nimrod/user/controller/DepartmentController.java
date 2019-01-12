package com.gioov.nimrod.user.controller;

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
@RequestMapping(Page.User.DEPARTMENT)
public class DepartmentController {

    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/USER/DEPARTMENT/PAGE_ALL')")
    @RequestMapping("/page_all")
    public String pageAll() {
        return Common.filterStartSlash(Page.User.DEPARTMENT + "/page_all");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/add_dialog")
    public String addDialog() {
        return Common.filterStartSlash(Page.User.DEPARTMENT + "/add_dialog");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/edit_dialog")
    public String editDialog() {
        return Common.filterStartSlash(Page.User.DEPARTMENT + "/edit_dialog");
    }

}
