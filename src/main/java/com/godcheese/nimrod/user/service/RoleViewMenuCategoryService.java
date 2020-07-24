package com.godcheese.nimrod.user.service;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface RoleViewMenuCategoryService {
    /**
     * 指定角色 id、视图菜单分类 id list，批量授权
     *
     * @param roleId                 角色 id
     * @param viewMenuCategoryIdList 视图菜单分类 id list
     * @return List<String>
     */
    int grantAllByRoleIdAndViewMenuCategoryIdList(Long roleId, List<Long> viewMenuCategoryIdList);

    /**
     * 指定角色 id、视图菜单分类 id list，批量撤销授权
     *
     * @param roleId                 角色 id
     * @param viewMenuCategoryIdList 视图菜单分类 id list
     * @return List<String>
     */
    int revokeAllByRoleIdAndViewMenuCategoryIdList(Long roleId, List<Long> viewMenuCategoryIdList);
}
