package com.gioov.nimrod.system.controller;

import com.gioov.nimrod.common.Common;
import com.gioov.nimrod.common.constant.Page;
import com.gioov.nimrod.common.operationlog.OperationLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.gioov.nimrod.common.constant.Page.INDEX;
import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

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
     * @return
     */
    @OperationLog(value = "首页")
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAnyAuthority('/','/INDEX','/SYSTEM','/SYSTEM/INDEX')")
    @RequestMapping(value = {"/", INDEX, Page.SYSTEM, Page.System.INDEX})
    public String index() {
        return Common.filterStartSlash(Page.SYSTEM + "/index");
    }

    /**
     * 工作台
     *
     * @return String
     */
    @OperationLog(value = "工作台")
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/SYSTEM/WORKBENCH')")
    @RequestMapping(value = Page.System.WORKBENCH)
    public String workbench() {
        return Common.filterStartSlash(Page.System.WORKBENCH);
    }

}
