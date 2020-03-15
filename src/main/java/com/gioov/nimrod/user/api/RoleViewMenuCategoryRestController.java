package com.gioov.nimrod.user.api;

import com.gioov.nimrod.user.User;
import com.gioov.nimrod.user.service.RoleViewMenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping(value =  User.Api.ROLE_VIEW_MENU_CATEGORY, produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleViewMenuCategoryRestController {

    private static final String ROLE_VIEW_MENU_CATEGORY = "/API/USER/ROLE_VIEW_MENU_CATEGORY";

    @Autowired
    private RoleViewMenuCategoryService roleViewMenuCategoryService;

    /**
     * 指定角色 id、视图菜单分类 id list，批量授权
     * @param roleId        角色 id
     * @param viewMenuCategoryIdList 视图菜单分类 id list
     * @return ResponseEntity<Integer>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ROLE_VIEW_MENU_CATEGORY + "/GRANT_ALL_BY_ROLE_ID_AND_VIEW_MENU_CATEGORY_ID_LIST')")
    @PostMapping(value = "/grant_all_by_role_id_and_view_menu_category_id_list")
    public ResponseEntity<Integer> grantAllByRoleIdAndViewMenuCategoryIdList(@RequestParam Long roleId, @RequestParam("viewMenuCategoryIdList[]") List<Long> viewMenuCategoryIdList) {
        return new ResponseEntity<>(roleViewMenuCategoryService.grantAllByRoleIdAndViewMenuCategoryIdList(roleId, viewMenuCategoryIdList), HttpStatus.OK);
    }

    /**
     * 指定角色 id、视图菜单分类 id list，批量撤销授权
     * @param roleId        角色 id
     * @param viewMenuCategoryIdList 视图菜单分类 id list
     * @return ResponseEntity<Integer>
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + ROLE_VIEW_MENU_CATEGORY + "/REVOKE_ALL_BY_ROLE_ID_AND_VIEW_MENU_CATEGORY_ID_LIST')")
    @PostMapping(value = "/revoke_all_by_role_id_and_view_menu_id_list")
    public ResponseEntity<Integer> revokeAllByRoleIdAndViewMenuCategoryIdList(@RequestParam Long roleId, @RequestParam("viewMenuCategoryIdList[]") List<Long> viewMenuCategoryIdList) {
        return new ResponseEntity<>(roleViewMenuCategoryService.revokeAllByRoleIdAndViewMenuCategoryIdList(roleId, viewMenuCategoryIdList), HttpStatus.OK);
    }
}
