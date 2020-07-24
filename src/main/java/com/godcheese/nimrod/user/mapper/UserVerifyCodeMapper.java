package com.godcheese.nimrod.user.mapper;

import com.godcheese.nimrod.user.entity.UserVerifyCodeEntity;
import com.godcheese.tile.mybatis.CrudMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("userVerifyCodeMapper")
@Mapper
public interface UserVerifyCodeMapper extends CrudMapper<UserVerifyCodeEntity, Long> {
    UserVerifyCodeEntity getOneByUserIdAndVerifyFrom(@Param("userId") Long userId, @Param("verifyFrom") String verifyFrom);

    int updateOneByUserIdAndVerifyFrom(UserVerifyCodeEntity userVerifyCodeEntity);

    int deleteAllByEmail(String email);
}
