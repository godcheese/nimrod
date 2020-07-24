package com.godcheese.nimrod.user.service;

import com.godcheese.nimrod.user.entity.RoleAuthorityEntity;

import java.util.List;
import java.util.Map;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface RoleAuthorityService {

    /**
     * 指定角色权限 id，获取角色权限
     *
     * @param id 角色权限 id
     * @return RoleAuthorityEntity
     */
    RoleAuthorityEntity getOne(Long id);

    /**
     * 指定角色 id、API 权限（authority），批量授权
     *
     * @param roleId           角色 id
     * @param apiAuthorityList API 权限（authority） list
     * @return List<String>
     */
    int grantAllByRoleIdAndApiAuthorityList(Long roleId, List<String> apiAuthorityList);

    /**
     * 指定角色 id、API 权限（authority），批量撤销授权
     *
     * @param roleId           角色 id
     * @param apiAuthorityList API 权限（authority） list
     * @return List<String>
     */
    int revokeAllByRoleIdAndApiAuthorityList(Long roleId, List<String> apiAuthorityList);

    /**
     * 指定角色 id、视图页面权限（authority） list，批量授权
     *
     * @param roleId            角色 id
     * @param viewPageAuthority 视图页面权限（authority） list
     * @return List<String>
     */
    int grantAllByRoleIdAndViewPageAuthorityList(Long roleId, List<String> viewPageAuthority);

    /**
     * 指定角色 id、视图页面权限（authority） list，批量撤销授权
     *
     * @param roleId            角色 id
     * @param viewPageAuthority 视图页面权限（authority） list
     * @return List<String>
     */
    int revokeAllByRoleIdAndViewPageAuthorityList(Long roleId, List<String> viewPageAuthority);

    /**
     * 指定角色 id、视图页面组件权限（authority），批量授权
     *
     * @param roleId                         角色 id
     * @param viewPageComponentAuthorityList 视图页面组件权限（authority） list
     * @return List<String>
     */
    int grantAllByRoleIdAndViewPageComponentAuthorityList(Long roleId, List<String> viewPageComponentAuthorityList);

    /**
     * 指定角色 id、视图页面组件权限（authority） list，批量撤销授权
     *
     * @param roleId                         角色 id
     * @param viewPageComponentAuthorityList 视图页面组件权限（authority） list
     * @return List<String>
     */
    int revokeAllByRoleIdAndViewPageComponentAuthorityList(Long roleId, List<String> viewPageComponentAuthorityList);

    /**
     * 指定角色 id、权限（authority） list，判断是否已授权
     *
     * @param roleId    角色 id
     * @param authority 权限（authority） list
     * @return Map<String, Object>
     */
    Map<String, Object> isGrantedByRoleIdAndAuthority(Long roleId, String authority);

}
