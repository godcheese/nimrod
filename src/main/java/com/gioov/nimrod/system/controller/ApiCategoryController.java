package com.gioov.nimrod.system.controller;

import com.gioov.nimrod.common.Common;
import com.gioov.nimrod.common.Url;
import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.system.System;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Controller
@RequestMapping(System.Page.API_CATEGORY)
public class ApiCategoryController {

    @OperationLog(value = "新增")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/add_dialog")
    public String addDialog() {
        return Common.trimSlash(System.Page.API_CATEGORY + "/add_dialog");
    }

    @OperationLog(value = "编辑")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/edit_dialog")
    public String editDialog() {
        return Common.trimSlash(System.Page.API_CATEGORY + "/edit_dialog");
    }

}
