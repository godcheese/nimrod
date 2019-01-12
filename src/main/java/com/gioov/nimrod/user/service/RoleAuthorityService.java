package com.gioov.nimrod.user.service;

import com.gioov.nimrod.user.entity.RoleAuthorityEntity;

import java.util.List;
import java.util.Map;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface RoleAuthorityService {

    /**
     * 指定角色 id、API 权限（authority），批量授权
     *
     * @param roleId        角色 id
     * @param authorityList 权限（authority） list
     * @return List<String>
     */
    List<String> grantAllByRoleIdAndApiAuthorityList(Long roleId, List<String> authorityList);

    /**
     * 指定角色 id、API 权限（authority），批量撤销授权
     *
     * @param roleId        角色 id
     * @param authorityList 权限（authority） list
     * @return List<String>
     */
    List<String> revokeAllByRoleIdAndApiAuthorityList(Long roleId, List<String> authorityList);

    /**
     * 指定角色 id、视图页面权限（authority），批量授权
     *
     * @param roleId        角色 id
     * @param authorityList 权限（authority） list
     * @return List<String>
     */
    List<String> grantAllByRoleIdAndPageAuthorityList(Long roleId, List<String> authorityList);

    /**
     * 指定角色 id、视图页面权限（authority），批量撤销授权
     *
     * @param roleId        角色 id
     * @param authorityList 权限（authority） list
     * @return List<String>
     */
    List<String> revokeAllByRoleIdAndPageAuthorityList(Long roleId, List<String> authorityList);

    /**
     * 指定角色 id、视图页面组件权限（authority），批量授权
     *
     * @param roleId        角色 id
     * @param authorityList 权限（authority） list
     * @return List<String>
     */
    List<String> grantAllByRoleIdAndPageComponentAuthorityList(Long roleId, List<String> authorityList);

    /**
     * 指定角色 id、视图页面组件权限（authority），批量撤销授权
     *
     * @param roleId        角色 id
     * @param authorityList 权限（authority） list
     * @return List<String>
     */
    List<String> revokeAllByRoleIdAndPageComponentAuthorityList(Long roleId, List<String> authorityList);

    /**
     * 指定角色 id、权限（authority）判断是否已授权
     *
     * @param roleId    角色 id
     * @param authority 权限（authority） list
     * @return Map<String                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               Object>
     */
    Map<String, Object> isGrantedByRoleIdAndAuthority(Long roleId, String authority);

    /**
     * 指定角色权限 id ，获取角色权限信息
     *
     * @param id 角色权限 id
     * @return RoleAuthorityEntity
     */
    RoleAuthorityEntity getOne(Long id);
}
