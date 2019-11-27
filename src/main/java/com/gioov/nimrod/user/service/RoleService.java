package com.gioov.nimrod.user.service;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.user.entity.RoleEntity;
import com.gioov.nimrod.user.entity.UserRoleEntity;
import com.gioov.tile.web.exception.BaseResponseException;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface RoleService {

    /**
     * 新增角色
     * @param roleEntity RoleEntity
     * @return RoleEntity
     * @throws BaseResponseException BaseResponseException
     */
    RoleEntity addOne(RoleEntity roleEntity) throws BaseResponseException;

    /**
     * 保存角色
     * @param roleEntity RoleEntity
     * @return RoleEntity
     * @throws BaseResponseException BaseResponseException
     */
    RoleEntity saveOne(RoleEntity roleEntity) throws BaseResponseException;

    /**
     * 指定角色 id list，批量删除角色
     * @param idList 角色 id list
     * @return int 已删除角色个数
     * @throws BaseResponseException BaseResponseException
     */
    int deleteAll(List<Long> idList) throws BaseResponseException;

    /**
     * 指定角色 id，获取角色
     * @param id 角色 id
     * @return RoleEntity
     */
    RoleEntity getOne(Long id);

    /**
     * 指定用户角色 list，获取所有角色
     * @param userRoleEntityList 用户角色 list
     * @return List<RoleEntity>
     */
    List<RoleEntity> listAllByUserRoleList(List<UserRoleEntity> userRoleEntityList);

    /**
     * 分页获取所有角色
     * @param page 页
     * @param rows 每页显示数量
     * @return Pagination<RoleEntity>
     */
    Pagination<RoleEntity> pageAll(Integer page, Integer rows);

    /**
     * 获取所有角色
     * @return List<RoleEntity>
     */
    List<RoleEntity> listAll();

    /**
     * 指定用户 id，获取角色
     * @param userId 用户 id
     * @return List<RoleEntity>
     */
    List<RoleEntity> listAllByUserId(Long userId);

}
