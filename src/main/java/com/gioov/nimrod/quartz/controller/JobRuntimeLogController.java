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
@RequestMapping(Quartz.Page.JOB_RUNTIME_LOG)
@Controller
public class JobRuntimeLogController {
    /**
     * 任务运行日志
     * @return String
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/QUARTZ/JOB_RUNTIME_LOG/LIST')")
    @RequestMapping("/list")
    public String list() {
        return Common.trimSlash(Quartz.Page.JOB_RUNTIME_LOG + "/list");
    }
}
