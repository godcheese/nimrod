package com.gioov.nimrod.user.api;

import com.gioov.nimrod.user.User;
import com.gioov.nimrod.user.entity.RoleAuthorityEntity;
import com.gioov.nimrod.user.service.RoleAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.gioov.nimrod.common.security.SimpleUserDetailsServiceImpl.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestController
@RequestMapping(value =  User.Api.ROLE_AUTHORITY, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RoleAuthorityRestController {

    private static final String ROLE_AUTHORITY = "/API/USER/ROLE_AUTHORITY";

    @Autowired
    private RoleAuthorityService roleAuthorityService;

    /**
     * 指定角色 id、API 权限（authority），批量授权
     * @param roleId        角色 id
     * @param authorityList 权限（authority） list
     * @return ResponseEntity<List < String>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ROLE_AUTHORITY + "/GRANT_ALL_BY_ROLE_ID_AND_API_AUTHORITY_LIST')")
    @PostMapping(value = "/grant_all_by_role_id_and_api_authority_list")
    public ResponseEntity<Integer> grantAllByRoleIdAndApiAuthorityList(@RequestParam Long roleId, @RequestParam("authorityList[]") List<String> authorityList) {
        return new ResponseEntity<>(roleAuthorityService.grantAllByRoleIdAndApiAuthorityList(roleId, authorityList), HttpStatus.OK);
    }

    /**
     * 指定角色 id、API 权限（authority），批量撤销授权
     * @param roleId        角色 id
     * @param authorityList 权限（authority） list
     * @return ResponseEntity<Integer>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ROLE_AUTHORITY + "/REVOKE_ALL_BY_ROLE_ID_AND_API_AUTHORITY_LIST')")
    @PostMapping(value = "/revoke_all_by_role_id_and_api_authority_list")
    public ResponseEntity<Integer> revokeAllByRoleIdAndApiAuthorityList(@RequestParam Long roleId, @RequestParam("authorityList[]") List<String> authorityList) {
        return new ResponseEntity<>(roleAuthorityService.revokeAllByRoleIdAndApiAuthorityList(roleId, authorityList), HttpStatus.OK);
    }

    /**
     * 指定角色 id、视图页面权限（authority），批量授权
     * @param roleId        角色 id
     * @param authorityList 权限（authority） list
     * @return ResponseEntity<Integer>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ROLE_AUTHORITY + "/GRANT_ALL_BY_ROLE_ID_AND_VIEW_PAGE_AUTHORITY_LIST')")
    @PostMapping(value = "/grant_all_by_role_id_and_view_page_authority_list")
    public ResponseEntity<Object> grantAllByRoleIdAndViewPageAuthorityList(@RequestParam Long roleId, @RequestParam("authorityList[]") List<String> authorityList) {
        return new ResponseEntity<>(roleAuthorityService.grantAllByRoleIdAndViewPageAuthorityList(roleId, authorityList), HttpStatus.OK);
    }

    /**
     * 指定角色 id、视图页面权限（authority），批量撤销授权
     * @param roleId        角色 id
     * @param authorityList 权限（authority） list
     * @return ResponseEntity<Integer>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ROLE_AUTHORITY + "/REVOKE_ALL_BY_ROLE_ID_AND_VIEW_PAGE_AUTHORITY_LIST')")
    @PostMapping(value = "/revoke_all_by_role_id_and_view_page_authority_list")
    public ResponseEntity<Integer> revokeAllByRoleIdAndViewPageAuthorityList(@RequestParam Long roleId, @RequestParam("authorityList[]") List<String> authorityList) {
        return new ResponseEntity<>(roleAuthorityService.revokeAllByRoleIdAndViewPageAuthorityList(roleId, authorityList), HttpStatus.OK);
    }

    /**
     * 指定角色 id、视图页面组件权限（authority），批量授权
     * @param roleId        角色 id
     * @param authorityList 权限（authority） list
     * @return ResponseEntity<Integer>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ROLE_AUTHORITY + "/GRANT_ALL_BY_ROLE_ID_AND_VIEW_PAGE_COMPONENT_AUTHORITY_LIST')")
    @PostMapping(value = "/grant_all_by_role_id_and_view_page_component_authority_list")
    public ResponseEntity<Integer> grantAllByRoleIdAndViewPageComponentAuthorityList(@RequestParam Long roleId, @RequestParam("authorityList[]") List<String> authorityList) {
        return new ResponseEntity<>(roleAuthorityService.grantAllByRoleIdAndViewPageComponentAuthorityList(roleId, authorityList), HttpStatus.OK);
    }

    /**
     * 指定角色 id、视图页面组件权限（authority），批量撤销授权
     * @param roleId        角色 id
     * @param authorityList 权限（authority） list
     * @return ResponseEntity<Integer>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ROLE_AUTHORITY + "/REVOKE_ALL_BY_ROLE_ID_AND_VIEW_PAGE_COMPONENT_AUTHORITY_LIST')")
    @PostMapping(value = "/revoke_all_by_role_id_and_view_page_component_authority_list")
    public ResponseEntity<Integer> revokeAllByRoleIdAndViewPageComponentAuthorityList(@RequestParam Long roleId, @RequestParam("authorityList[]") List<String> authorityList) {
        return new ResponseEntity<>(roleAuthorityService.revokeAllByRoleIdAndViewPageComponentAuthorityList(roleId, authorityList), HttpStatus.OK);
    }

    /**
     * 指定角色 id、权限（authority）判断是否已授权
     * @param roleId    角色 id
     * @param authority 权限（authority） list
     * @return ResponseEntity<Map<String, Object>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ROLE_AUTHORITY + "/IS_GRANTED_BY_ROLE_ID_AND_AUTHORITY')")
    @GetMapping(value = "/is_granted_by_role_id_and_authority")
    public ResponseEntity<Map<String, Object>> isGrantedByRoleIdAndAuthority(@RequestParam Long roleId, @RequestParam String authority) {
        return new ResponseEntity<>(roleAuthorityService.isGrantedByRoleIdAndAuthority(roleId, authority), HttpStatus.OK);
    }

    /**
     * 指定角色权限 id，获取角色权限信息
     * @param id 角色权限 id
     * @return ResponseEntity<RoleAuthorityEntity>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ROLE_AUTHORITY + "/ONE')")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<RoleAuthorityEntity> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(roleAuthorityService.getOne(id), HttpStatus.OK);
    }

}
