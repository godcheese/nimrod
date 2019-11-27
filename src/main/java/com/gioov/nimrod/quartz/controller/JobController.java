package com.gioov.nimrod.quartz.controller;

import com.gioov.nimrod.common.others.Common;
import com.gioov.nimrod.quartz.Quartz;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.gioov.nimrod.common.security.SimpleUserDetailsServiceImpl.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-02-11
 */
@RequestMapping(Quartz.Page.JOB)
@Controller
public class JobController {

    /**
     * 定时任务 页面
     * @return String
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/QUARTZ/LIST')")
    @RequestMapping("/list")
    public String list() {
        return Common.trimSlash(Quartz.Page.JOB + "/list");
    }

    /**
     * 定时任务新增 对话框
     * @return String
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/add_dialog")
    public String addDialog() {
        return Common.trimSlash(Quartz.Page.JOB + "/add_dialog");
    }

    /**
     * 定时任务编辑 对话框
     * @return String
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/edit_dialog")
    public String editDialog() {
        return Common.trimSlash(Quartz.Page.JOB + "/edit_dialog");
    }

}
