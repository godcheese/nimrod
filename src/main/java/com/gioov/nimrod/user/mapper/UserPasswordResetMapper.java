package com.gioov.nimrod.user.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.nimrod.user.entity.UserPasswordResetEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("userPasswordResetMapper")
@Mapper
public interface UserPasswordResetMapper extends CrudMapper<UserPasswordResetEntity, Long> {

}
