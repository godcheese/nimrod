package com.gioov.nimrod.system.api;

import com.gioov.nimrod.system.System;
import com.gioov.nimrod.system.service.ViewPageComponentApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestController
@RequestMapping(System.Api.VIEW_PAGE_COMPONENT_API)
public class ViewPageComponentApiRestController {

    private static final String VIEW_PAGE_COMPONENT_API = "/API/SYSTEM/VIEW_PAGE_COMPONENT_API";

    @Autowired
    private ViewPageComponentApiService viewPageComponentApiService;

    /**
     * 是否关联 API
     *
     * @param pageComponentId 视图页面组件 id
     * @param apiId           API id
     * @return ResponseEntity<Map<String, Object>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_COMPONENT_API + "/IS_ASSOCIATED_BY_PAGE_COMPONENT_ID_AND_API_ID')")
    @GetMapping(value = "/is_associated_by_page_component_id_and_api_id")
    public ResponseEntity<Map<String, Object>> isAssociatedByPageComponentIdAndApiId(@RequestParam Long pageComponentId, @RequestParam Long apiId) {
        return new ResponseEntity<>(viewPageComponentApiService.isAssociatedByPageComponentIdAndApiId(pageComponentId, apiId), HttpStatus.OK);
    }

    /**
     * 批量关联 API
     *
     * @param pageComponentId 视图页面组件 id
     * @param apiIdList       API id list
     * @return ResponseEntity<List<Long>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_COMPONENT_API + "/ASSOCIATE_ALL_BY_PAGE_COMPONENT_ID_AND_API_ID_LIST')")
    @PostMapping(value = "/associate_all_by_page_component_id_and_api_id_list")
    public ResponseEntity<List<Long>> associateAllByPageComponentIdAndApiIdList(@RequestParam Long pageComponentId, @RequestParam("apiIdList[]") List<Long> apiIdList) {
        return new ResponseEntity<>(viewPageComponentApiService.associateAllByPageComponentIdAndApiIdList(pageComponentId, apiIdList), HttpStatus.OK);
    }

    /**
     * 指定视图页面组件 id、API id ，批量撤销关联
     *
     * @param pageComponentId 视图页面组件 id
     * @param apiIdList       API id list
     * @return ResponseEntity<Integer>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_COMPONENT_API + "/REVOKE_ALL_BY_ROLE_ID_AND_AUTHORITY')")
    @PostMapping(value = "/revoke_associate_all_by_page_component_id_and_api_id_list")
    public ResponseEntity<Integer> revokeAssociateAllByPageComponentIdAndApiIdList(@RequestParam Long pageComponentId, @RequestParam("apiIdList[]") List<Long> apiIdList) {
        return new ResponseEntity<>(viewPageComponentApiService.revokeAssociateAllByPageComponentIdAndApiIdList(pageComponentId, apiIdList), HttpStatus.OK);
    }

}
