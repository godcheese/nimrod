package com.gioov.nimrod.user.api;

import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.common.operationlog.OperationLogType;
import com.gioov.nimrod.user.User;
import com.gioov.nimrod.user.service.ViewPageApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.gioov.nimrod.common.security.SimpleUserDetailsServiceImpl.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestController
@RequestMapping(User.Api.VIEW_PAGE_API)
public class ViewPageApiRestController {

    private static final String VIEW_PAGE_API = "/API/SYSTEM/VIEW_PAGE_API";

    @Autowired
    private ViewPageApiService viewPageApiService;

//    /**
//     * 是否关联 API
//     * @param viewPageId 视图页面 id
//     * @param apiId  API id
//     * @return ResponseEntity<Map < String ,   Object>>
//     */
//    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_API + "/IS_ASSOCIATED_BY_VIEW_PAGE_ID_AND_API_ID')")
//    @GetMapping(value = "/is_associated_by_vie_page_id_and_api_id")
//    public ResponseEntity<Map<String, Object>> isAssociatedByViewPageIdAndApiId(@RequestParam Long viewPageId, @RequestParam Long apiId) {
//        return new ResponseEntity<>(viewPageApiService.isAssociatedByViewPageIdAndApiId(viewPageId, apiId), HttpStatus.OK);
//    }

    /**
     * 指定视图页面 id，API id list，批量关联
     *
     * @param viewPageId 视图页面 id
     * @param apiIdList  API id list
     * @return ResponseEntity<Integer>
     */
    @OperationLog(value = "指定视图页面 id，API id list，批量关联", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_API + "/ASSOCIATE_ALL_BY_VIEW_PAGE_ID_AND_API_ID_LIST')")
    @PostMapping(value = "/associate_all_by_view_page_id_and_api_id_list")
    public ResponseEntity<Integer> associateAllByViewPageIdAndApiIdList(@RequestParam Long viewPageId, @RequestParam("apiIdList[]") List<Long> apiIdList) {
        return new ResponseEntity<>(viewPageApiService.associateAllByViewPageIdAndApiIdList(viewPageId, apiIdList), HttpStatus.OK);
    }

    /**
     * 指定视图页面 id，API id list，批量撤销关联
     *
     * @param viewPageId 视图页面 id
     * @param apiIdList  API id list
     * @return ResponseEntity<Integer>
     */
    @OperationLog(value = "指定视图页面 id，API id list，批量撤销关联", type = OperationLogType.API)
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + VIEW_PAGE_API + "/REVOKE_ASSOCIATE_ALL_BY_VIEW_PAGE_ID_AND_API_ID_LIST')")
    @PostMapping(value = "/revoke_associate_all_by_view_page_id_and_api_id_list")
    public ResponseEntity<Integer> revokeAssociateAllByViewPageIdAndApiIdList(@RequestParam Long viewPageId, @RequestParam("apiIdList[]") List<Long> apiIdList) {
        return new ResponseEntity<>(viewPageApiService.revokeAssociateAllByViewPageIdAndApiIdList(viewPageId, apiIdList), HttpStatus.OK);
    }

}
