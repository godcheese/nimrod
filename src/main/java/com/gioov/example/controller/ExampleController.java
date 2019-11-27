package com.gioov.example.controller;

import com.gioov.example.Example;
import com.gioov.nimrod.common.others.Common;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Controller
@RequestMapping(Example.Page.EXAMPLE)
public class ExampleController {

    /**
     * 测试页面
     * @return String
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/test")
    public String pageAll() {
        return  Common.trimSlash(Example.Page.EXAMPLE + "/test");
    }

}
