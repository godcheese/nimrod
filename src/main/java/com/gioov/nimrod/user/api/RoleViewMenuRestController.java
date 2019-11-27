package com.gioov.nimrod.user.api;

import com.gioov.nimrod.user.User;
import com.gioov.nimrod.user.entity.RoleAuthorityEntity;
import com.gioov.nimrod.user.service.RoleAuthorityService;
import com.gioov.nimrod.user.service.RoleViewMenuService;
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
@RequestMapping(value =  User.Api.ROLE_VIEW_MENU, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RoleViewMenuRestController {

    private static final String ROLE_VIEW_MENU = "/API/USER/ROLE_VIEW_MENU";

    @Autowired
    private RoleViewMenuService roleViewMenuService;

    /**
     * 指定角色 id、视图菜单 id list，批量授权
     * @param roleId        角色 id
     * @param viewMenuIdList 视图菜单 id list
     * @return ResponseEntity<List < String>>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ROLE_VIEW_MENU + "/GRANT_ALL_BY_ROLE_ID_AND_VIEW_MENU_ID_LIST')")
    @PostMapping(value = "/grant_all_by_role_id_and_view_menu_id_list")
    public ResponseEntity<Integer> grantAllByRoleIdAndViewMenuIdList(@RequestParam Long roleId, @RequestParam("viewMenuIdList[]") List<Long> viewMenuIdList) {
        return new ResponseEntity<>(roleViewMenuService.grantAllByRoleIdAndViewMenuIdList(roleId, viewMenuIdList), HttpStatus.OK);
    }

    /**
     * 指定角色 id、视图菜单 id list，批量撤销授权
     * @param roleId        角色 id
     * @param viewMenuIdList 视图菜单 id list
     * @return ResponseEntity<Integer>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ROLE_VIEW_MENU + "/REVOKE_ALL_BY_ROLE_ID_AND_VIEW_MENU_ID_LIST')")
    @PostMapping(value = "/revoke_all_by_role_id_and_view_menu_id_list")
    public ResponseEntity<Integer> revokeAllByRoleIdAndViewMenuIdList(@RequestParam Long roleId, @RequestParam("viewMenuIdList[]") List<Long> viewMenuIdList) {
        return new ResponseEntity<>(roleViewMenuService.revokeAllByRoleIdAndViewMenuIdList(roleId, viewMenuIdList), HttpStatus.OK);
    }
}
