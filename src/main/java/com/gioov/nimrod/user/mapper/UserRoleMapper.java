package com.gioov.nimrod.user.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.nimrod.user.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("userRoleMapper")
@Mapper
public interface UserRoleMapper extends CrudMapper<UserRoleEntity, Long> {

    /**
     * 指定用户 id ，获取用户角色
     *
     * @param userId 用户 id
     * @return List<UserRoleEntity>
     */
    List<UserRoleEntity> listAllByUserId(@Param("userId") Long userId);

    /**
     * 指定角色 id ，获取用户角色
     *
     * @param roleId 角色 id
     * @return UserRoleEntity
     */
    UserRoleEntity getOneByRoleId(@Param("roleId") Long roleId);

    /**
     * 指定用户 id 、角色 id list ，删除所有
     *
     * @param userId     用户 id
     * @param roleIdList 角色 id list
     * @return int
     */
    int deleteAllByUserIdAndRoleIdList(@Param("userId") Long userId, @Param("roleIdList") List<Long> roleIdList);

    /**
     * 指定用户 id 、角色 id ，获取用户角色
     *
     * @param userId 用户 id
     * @param roleId 角色 id
     * @return UserRoleEntity
     */
    UserRoleEntity getOneByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") Long roleId);

    /**
     * 指定角色 id ，删除所有
     *
     * @param roleId 角色 id
     * @return int
     */
    int deleteAllByRoleId(@Param("roleId") Long roleId);

}
