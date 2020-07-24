package com.godcheese.nimrod.system.controller;

import com.godcheese.nimrod.common.others.Common;
import com.godcheese.nimrod.system.System;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.godcheese.nimrod.common.security.SimpleUserDetailsServiceImpl.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Controller
@RequestMapping(System.Page.OPERATION_LOG)
public class OperationLogController {
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/SYSTEM/OPERATION_LOG/LIST')")
    @RequestMapping("/list")
    public String list() {
        return Common.trimSlash(System.Page.OPERATION_LOG + "/list");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/view_dialog")
    public String viewDialog() {
        return Common.trimSlash(System.Page.OPERATION_LOG + "/view_dialog");
    }
}
