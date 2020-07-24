package com.godcheese.nimrod.user.api;

import com.godcheese.nimrod.common.operationlog.OperationLog;
import com.godcheese.nimrod.common.operationlog.OperationLogType;
import com.godcheese.nimrod.user.User;
import com.godcheese.nimrod.user.service.ViewPageComponentApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.godcheese.nimrod.common.security.SimpleUserDetailsServiceImpl.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestController
@RequestMapping(User.Api.VIEW_PAGE_COMPONENT_API)
public class ViewPageComponentApiRestController {

    private static final String VIEW_PAGE_COMPONENT_API = "/API/SYSTEM/VIEW_PAGE_COMPONENT_API";

    @Autowired
    private ViewPageComponentApiService viewPageComponentApiService;

//    /**
//     * 是否关联 API
//     * @param viewPageComponentId 视图页面组件 id
//     * @param apiId API id
//     * @return ResponseEntity<Map<String, Object>>
//     */
//    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_COMPONENT_API + "/IS_ASSOCIATED_BY_VIEW_PAGE_COMPONENT_ID_AND_API_ID')")
//    @GetMapping(value = "/is_associated_by_view_page_component_id_and_api_id")
//    public ResponseEntity<Map<String, Object>> isAssociatedByViewPageComponentIdAndApiId(@RequestParam Long viewPageComponentId, @RequestParam Long apiId) {
//        return new ResponseEntity<>(viewPageComponentApiService.isAssociatedByViewPageComponentIdAndApiId(viewPageComponentId, apiId), HttpStatus.OK);
//    }

    /**
     * 指定视图页面组件 id、API id list，批量关联
     *
     * @param viewPageComponentId 视图页面组件 id
     * @param apiIdList           API id list
     * @return ResponseEntity<Integer>
     */
    @OperationLog(value = "指定视图页面组件 id、API id list，批量关联", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_COMPONENT_API + "/ASSOCIATE_ALL_BY_VIEW_PAGE_COMPONENT_ID_AND_API_ID_LIST')")
    @PostMapping(value = "/associate_all_by_view_page_component_id_and_api_id_list")
    public ResponseEntity<Integer> associateAllByViewPageComponentIdAndApiIdList(@RequestParam Long viewPageComponentId, @RequestParam("apiIdList[]") List<Long> apiIdList) {
        return new ResponseEntity<>(viewPageComponentApiService.associateAllByViewPageComponentIdAndApiIdList(viewPageComponentId, apiIdList), HttpStatus.OK);
    }

    /**
     * 指定视图页面组件 id、API id，批量撤销关联
     *
     * @param viewPageComponentId 视图页面组件 id
     * @param apiIdList           API id list
     * @return ResponseEntity<Integer>
     */
    @OperationLog(value = "指定视图页面组件 id、API id，批量撤销关联", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_COMPONENT_API + "/REVOKE_ASSOCIATE_ALL_BY_ROLE_ID_AND_AUTHORITY')")
    @PostMapping(value = "/revoke_associate_all_by_view_page_component_id_and_api_id_list")
    public ResponseEntity<Integer> revokeAssociateAllByViewPageComponentIdAndApiIdList(@RequestParam Long viewPageComponentId, @RequestParam("apiIdList[]") List<Long> apiIdList) {
        return new ResponseEntity<>(viewPageComponentApiService.revokeAssociateAllByViewPageComponentIdAndApiIdList(viewPageComponentId, apiIdList), HttpStatus.OK);
    }
}
