package com.godcheese.nimrod.user.service;

import com.godcheese.nimrod.user.entity.UserVerifyCodeEntity;
import com.godcheese.tile.web.exception.BaseResponseException;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface UserVerifyCodeService {
    /**
     * @param userId
     * @param verifyFrom
     * @return
     */
    UserVerifyCodeEntity addOne(UserVerifyCodeEntity userVerifyCodeEntity) throws BaseResponseException;

    /**
     * 指定用户 id、角色 id list，批量删除用户角色
     *
     * @param userId     用户 id
     * @param roleIdList 角色 id list
     * @return 已删除角色个数
     */
    int deleteAllByUserIdAndVerifyFrom(Long userId, String verifyFrom);

    boolean isExpires(UserVerifyCodeEntity userVerifyCodeEntity);

    UserVerifyCodeEntity getOneByUserIdAndVerifyFrom(Long userId, String verifyFrom, boolean isExpires, String comparisonVerifyCode) throws BaseResponseException;
}