package com.gioov.nimrod.user.service;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface RoleViewMenuService {
    /**
     * 指定角色 id、视图菜单 id list，批量授权
     * @param roleId 角色 id
     * @param viewMenuIdList 视图菜单 id list
     * @return List<String>
     */
    int grantAllByRoleIdAndViewMenuIdList(Long roleId, List<Long> viewMenuIdList);

    /**
     * 指定角色 id、视图菜单 id list，批量撤销授权
     * @param roleId 角色 id
     * @param viewMenuIdList 视图菜单 id list
     * @return List<String>
     */
    int revokeAllByRoleIdAndViewMenuIdList(Long roleId, List<Long> viewMenuIdList);
}
