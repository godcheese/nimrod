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
@RequestMapping(System.Page.FILE)
public class FileController {
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/SYSTEM/FILE/LIST')")
    @RequestMapping("/list")
    public String list() {
        return Common.trimSlash(System.Page.FILE + "/list");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/upload_one_dialog")
    public String uploadOneDialog() {
        return Common.trimSlash(System.Page.FILE + "/upload_one_dialog");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/upload_all_dialog")
    public String uploadAllDialog() {
        return Common.trimSlash(System.Page.FILE + "/upload_all_dialog");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/edit_dialog")
    public String editDialog() {
        return Common.trimSlash(System.Page.FILE + "/edit_dialog");
    }
}
