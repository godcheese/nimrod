package com.gioov.nimrod.user.service;

import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.security.SimpleUser;
import com.gioov.nimrod.user.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface UserService {

    String SYSTEM_ADMIN = "SYSTEM_ADMIN";

    /**
     * 指定用户 id 、密码，获取用户
     *
     * @param id       用户 id
     * @param password 用户密码
     * @return UserEntity
     */
    UserEntity getOneByIdAndPassword(Long id, String password);

    /**
     * 指定用户名、密码，获取用户
     *
     * @param username 用户名
     * @param password 用户密码
     * @return UserEntity
     */
    UserEntity getOneByUsernameAndPassword(String username, String password);

    /**
     * 指定电子邮箱、密码，获取用户
     *
     * @param mail    电子邮箱
     * @param password 用户密码
     * @return UserEntity
     */
    UserEntity getOneByMailAndPassword(String mail, String password);

    /**
     * 指定手机号、密码，获取用户
     *
     * @param cellphone 手机号
     * @param password  用户密码
     * @return UserEntity
     */
    UserEntity getOneByCellphoneAndPassword(String cellphone, String password);

    /**
     * 校验密码是否正确
     *
     * @param plainPassword  明文密码
     * @param cipherPassword 密文密码
     * @return boolean
     */
    boolean checkPassword(String plainPassword, String cipherPassword);

    /**
     * 加密明文密码
     *
     * @param plainPassword 明文密码
     * @return String
     */
    String encodePassword(String plainPassword);

    /**
     * 指定用户 id ，获取用户
     *
     * @param id 用户 id
     * @return UserEntity
     */
    UserEntity getOneByIdNoPassword(Long id);

    /**
     * 伪删除用户
     *
     * @param idList id list
     */
    int fakeDeleteAll(List<Long> idList);

    /**
     * 撤销伪删除用户
     *
     * @param idList 用户 id list
     */
    int revokeFakeDeleteAll(List<Long> idList);

    /**
     * 可能会 null
     *
     * @return UserEntity
     */
    UserEntity getCurrentUser();

    /**
     * 更可靠的获取，但需指定 HttpServletRequest
     *
     * @param request HttpServletRequest
     * @return UserEntity
     */
    UserEntity getCurrentUser(HttpServletRequest request);

    /**
     * 可能会 null
     *
     * @return UserEntity
     */
    SimpleUser getCurrentSimpleUser();

    /**
     * 更可靠的获取，但需指定 HttpServletRequest
     *
     * @param request HttpServletRequest
     * @return UserEntity
     */
    SimpleUser getCurrentSimpleUser(HttpServletRequest request);

//    /**
//     * 注销当前用户
//     *
//     * @param httpServletRequest  HttpServletRequest
//     * @param httpServletResponse HttpServletResponse
//     */
//    void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws BaseResponseException;

    /**
     * 指定 API 分类 id ，分页获取所有 API
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return Pagination.Result<UserEntity>
     */
    Pagination.Result<UserEntity> pageAll(Integer page, Integer rows);

    /**
     * 指定 API 分类 id ，分页获取所有 API
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return Pagination.Result<UserEntity>
     */
    Pagination.Result<UserEntity> pageAllByDepartmentId(Long departmentId, Integer page, Integer rows);

    /**
     * 新增角色
     *
     * @param userEntity UserEntity
     * @return UserEntity
     */
    UserEntity insertOne(UserEntity userEntity) throws BaseResponseException;

    /**
     * 保存角色
     *
     * @param userEntity UserEntity
     * @return UserEntity
     */
    UserEntity updateOne(UserEntity userEntity) throws BaseResponseException;

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
     * @return UserEntity
     */
    UserEntity getOne(Long id);
}



