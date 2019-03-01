package com.gioov.nimrod.user.controller;

import com.gioov.nimrod.common.Common;
import com.gioov.nimrod.common.Url;
import com.gioov.nimrod.user.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Controller
@RequestMapping(User.Page.VIEW_MENU_CATEGORY)
public class ViewMenuCategoryController {

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/add_dialog")
    public String addDialog() {
        return Common.trimSlash(User.Page.VIEW_MENU_CATEGORY + "/add_dialog");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/edit_dialog")
    public String editDialog() {
        return Common.trimSlash(User.Page.VIEW_MENU_CATEGORY + "/edit_dialog");
    }

}
