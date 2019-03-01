package com.gioov.nimrod.quartz.controller;

import com.gioov.nimrod.common.Common;
import com.gioov.nimrod.quartz.Quartz;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-02-11
 */
@RequestMapping(Quartz.Page.JOB_RUNTIME_LOG)
@Controller
public class JobRuntimeLogController {

    /**
     * 定时任务 对话框
     *
     * @return String
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/QUARTZ/JOB_RUNTIME_LOG/PAGE_ALL_DIALOG')")
    @RequestMapping("/page_all_dialog")
    public String pageAllDialog() {
        return Common.trimSlash(Quartz.Page.JOB_RUNTIME_LOG + "/page_all_dialog");
    }

}
