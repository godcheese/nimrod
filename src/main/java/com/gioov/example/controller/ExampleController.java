package com.gioov.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Controller
@RequestMapping("/example")
public class ExampleController {

    /**
     * 测试页面
     *
     * @return String
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/test")
    public String pageAll() {
        return "example/test";
    }
}
