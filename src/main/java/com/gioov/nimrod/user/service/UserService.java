package com.gioov.nimrod.user.service;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.user.entity.UserEntity;
import com.gioov.tile.web.exception.BaseResponseException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface UserService {

    /**
     * 新增用户
     * @param userEntity UserEntity
     * @return UserEntity
     * @throws BaseResponseException BaseResponseException
     */
    UserEntity addOne(UserEntity userEntity) throws BaseResponseException;

    /**
     * 保存角色
     * @param userEntity UserEntity
     * @return UserEntity
     * @throws BaseResponseException BaseResponseException
     */
    UserEntity saveOne(UserEntity userEntity) throws BaseResponseException;

    /**
     * 指定用户 id list，批量永久删除用户
     * @param idList 用户 id list
     * @return int 已删除角色个数
     */
    int deleteAll(List<Long> idList);

    /**
     * 指定用户 id，获取用户
     * @param id 用户 id
     * @return UserEntity
     */
    UserEntity getOne(Long id);

    /**
     * 获取当前用户，可能会 null
     * @return UserEntity
     */
    UserEntity getCurrentUser();

    /**
     * 获取当前用户，可能会 null
     * @return UserEntity
     */
    UserEntity getCurrentUserNoPassword();

    /**
     * 获取当前用户，更可靠的获取，但需指定 HttpServletRequest
     * @param request HttpServletRequest
     * @return UserEntity
     */
    UserEntity getCurrentUser(HttpServletRequest request);

    /**
     * 获取当前 SimpleUser，可能会 null
     * @return UserEntity
     */
//     SimpleUser getCurrentSimpleUser();

    /**
     * 获取当前 SimpleUser，更可靠的获取，但需指定 HttpServletRequest
     * @param request HttpServletRequest
     * @return UserEntity
     */
//    static SimpleUser getCurrentSimpleUser(HttpServletRequest request);

    /**
     * 指定用户 id、密码，获取用户
     * @param id       用户 id
     * @param password 用户密码
     * @return UserEntity
     */
    UserEntity getOneByIdAndPassword(Long id, String password);

    /**
     * 指定用户名、密码，获取用户
     * @param username 用户名
     * @param password 用户密码
     * @return UserEntity
     */
    UserEntity getOneByUsernameAndPassword(String username, String password);

    /**
     * 指定电子邮箱、密码，获取用户
     * @param email    电子邮箱
     * @param password 密码
     * @return UserEntity
     */
    UserEntity getOneByEmailAndPassword(String email, String password);

    /**
     * 指定手机号码、密码，获取用户
     * @param cellphone 手机号
     * @param password  密码
     * @return UserEntity
     */
    UserEntity getOneByCellphoneAndPassword(String cellphone, String password);

    /**
     * 校验密码是否正确
     * @param plainPassword  明文密码
     * @param cipherPassword 密文密码
     * @return boolean
     */
    boolean checkPassword(String plainPassword, String cipherPassword);

    /**
     * 加密明文密码
     * @param plainPassword 明文密码
     * @return String
     */
    String encodePassword(String plainPassword);

    /**
     * 指定用户 id，获取用户（去掉密码）
     * @param id 用户 id
     * @return UserEntity
     */
    UserEntity getOneByIdNoPassword(Long id);

    /**
     * 指定用户 id list，批量删除用户
     * @param idList 用户 id list
     * @return int
     */
    int fakeDeleteAll(List<Long> idList);

    /**
     * 指定用户 id list，批量撤销删除用户
     * @param idList 用户 id list
     * @return int
     */
    int revokeFakeDeleteAll(List<Long> idList);

//    /**
//     * 注销当前用户
//     * @param httpServletRequest  HttpServletRequest
//     * @param httpServletResponse HttpServletResponse
//     */
//    void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws BaseResponseException;

    /**
     * 分页获取所有用户
     * @param page            页
     * @param rows            每页显示数量
     * @param sorterField     sorterField
     * @param sorterOrder     sorterOrder
     * @param userEntity      userEntity
     * @param gmtCreatedStart gmtCreatedStart
     * @param gmtCreatedEnd   gmtCreatedEnd
     * @param gmtDeletedStart gmtDeletedStart
     * @param gmtDeletedEnd   gmtDeletedEnd
     * @return Pagination<UserEntity>
     */
    Pagination<UserEntity> pageAll(Integer page, Integer rows, String sorterField, String sorterOrder, UserEntity userEntity, String gmtCreatedStart, String gmtCreatedEnd, String gmtDeletedStart, String gmtDeletedEnd);

    /**
     * 指定部门 id，分页获取所有用户
     * @param departmentId 部门 id
     * @param page         页
     * @param rows         每页显示数量
     * @return Pagination<UserEntity>
     */
    Pagination<UserEntity> pageAllByDepartmentId(Long departmentId, Integer page, Integer rows);

    UserEntity profile(UserEntity userEntity);

    UserEntity saveProfile(UserEntity userEntity) throws BaseResponseException;

    boolean sendEmailVerifyCode(Long userId, String email) throws BaseResponseException;

    boolean sendEmailVerifyCode(UserEntity userEntity) throws BaseResponseException;

    boolean checkEmailVerifyCode(UserEntity userEntity, String email, String emailVerifyCode) throws BaseResponseException;

    boolean changeEmail(UserEntity userEntity, String emailVerifyCode, String newEmail, String newEmailVerifyCode) throws BaseResponseException;

    boolean changePassword(UserEntity userEntity, String password, String newPassword, String confirmNewPassword) throws BaseResponseException;
}



