package com.gioov.nimrod.flowable.controller;

import com.gioov.nimrod.flowable.Url;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-01-21
 */
@RequestMapping(Url.Page.ACT_HI_PROCINST)
@Controller
public class ActHiProcinstController {

    @GetMapping("/page_all")
    public String pageAll() {
        return com.gioov.nimrod.common.Common.trimSlash(Url.Page.ACT_HI_PROCINST + "/page_all");
    }

    @GetMapping("/delete_all_dialog")
    public String deleteAllDialog() {
        return com.gioov.nimrod.common.Common.trimSlash(Url.Page.ACT_HI_PROCINST + "/delete_all_dialog");
    }

}
