package com.godcheese.nimrod.system.controller;

import com.godcheese.nimrod.common.Url;
import com.godcheese.nimrod.common.operationlog.OperationLog;
import com.godcheese.nimrod.common.others.Common;
import com.godcheese.nimrod.system.System;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.godcheese.nimrod.common.security.SimpleUserDetailsServiceImpl.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Controller
@RequestMapping
public class SystemController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemController.class);

    @Value("${server.error.path}")
    private String serverErrorPath;

    /**
     * 用户登录后首页
     *
     * @return String
     */
    @OperationLog(value = "首页")
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAnyAuthority('/','/INDEX','/SYSTEM','/SYSTEM/INDEX')")
    @RequestMapping(value = {"/", Url.Page.INDEX, System.Page.SYSTEM, System.Page.INDEX})
    public String index() {
        return Common.trimSlash(System.Page.SYSTEM + "/index");
    }

    /**
     * 工作台
     *
     * @return String
     */
    @OperationLog(value = "工作台")
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/SYSTEM/WORKBENCH')")
    @RequestMapping(value = System.Page.WORKBENCH)
    public String workbench() {
        return Common.trimSlash(System.Page.WORKBENCH);
    }
}
