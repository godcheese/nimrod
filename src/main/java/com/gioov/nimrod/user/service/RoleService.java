package com.gioov.nimrod.user.service;

import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.user.entity.RoleEntity;
import com.gioov.nimrod.user.entity.UserRoleEntity;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface RoleService {

    /**
     * 指定用户角色 list ，获取所有角色
     *
     * @param userRoleEntityList 用户角色 list
     * @return List<RoleEntity>
     */
    List<RoleEntity> listAllByUserRoleList(List<UserRoleEntity> userRoleEntityList);

    /**
     * 指定 API 分类 id ，分页获取所有 API
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return Pagination.Result<RoleEntity>
     */
    Pagination.Result<RoleEntity> pageAll(Integer page, Integer rows);

    /**
     * 获取所有角色
     *
     * @return List<RoleEntity>
     */
    List<RoleEntity> listAll();

    /**
     * 指定用户 id ，获取用户角色
     *
     * @param userId 用户 id
     * @return List<RoleEntity>
     */
    List<RoleEntity> listAllByUserId(Long userId);

    /**
     * 新增角色
     *
     * @param roleEntity RoleEntity
     * @return RoleEntity
     */
    RoleEntity insertOne(RoleEntity roleEntity) throws BaseResponseException;

    /**
     * 保存角色
     *
     * @param roleEntity RoleEntity
     * @return RoleEntity
     */
    RoleEntity updateOne(RoleEntity roleEntity) throws BaseResponseException;

    /**
     * 指定角色 id list ，批量删除角色
     *
     * @param idList 角色 id list
     * @return 已删除角色个数
     */
    int deleteAll(List<Long> idList);

    /**
     * 指定角色 id ，获取角色信息
     *
     * @param id 角色 id
     * @return RoleEntity
     */
    RoleEntity getOne(Long id);

}
