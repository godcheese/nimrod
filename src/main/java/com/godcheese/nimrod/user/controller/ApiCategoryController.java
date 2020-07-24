package com.godcheese.nimrod.user.controller;

import com.godcheese.nimrod.common.operationlog.OperationLog;
import com.godcheese.nimrod.common.others.Common;
import com.godcheese.nimrod.user.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Controller
@RequestMapping(User.Page.API_CATEGORY)
public class ApiCategoryController {
    @OperationLog(value = "新增")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/add_dialog")
    public String addDialog() {
        return Common.trimSlash(User.Page.API_CATEGORY + "/add_dialog");
    }

    @OperationLog(value = "编辑")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/edit_dialog")
    public String editDialog() {
        return Common.trimSlash(User.Page.API_CATEGORY + "/edit_dialog");
    }
}
