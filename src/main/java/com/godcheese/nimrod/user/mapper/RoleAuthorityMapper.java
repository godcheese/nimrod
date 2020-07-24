package com.godcheese.nimrod.user.mapper;

import com.godcheese.nimrod.user.entity.RoleAuthorityEntity;
import com.godcheese.tile.mybatis.CrudMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("roleAuthorityMapper")
@Mapper
public interface RoleAuthorityMapper extends CrudMapper<RoleAuthorityEntity, Long> {

    /**
     * 指定角色 id，获取所有角色权限
     *
     * @param roleId 角色 id
     * @return List<RoleAuthorityEntity>
     */
    List<RoleAuthorityEntity> listAllByRoleId(@Param("roleId") Long roleId);

    /**
     * 指定角色 id、authority，获取角色权限
     *
     * @param roleId    角色 id
     * @param authority authority
     * @return RoleAuthorityEntity
     */
    RoleAuthorityEntity getOneByRoleIdAndAuthority(@Param("roleId") Long roleId, @Param("authority") String authority);

    /**
     * 指定角色 id、authority list，批量删除
     *
     * @param roleId        角色 id
     * @param authorityList authority list
     * @return int
     */
    int deleteAllByRoleIdAndAuthorityList(@Param("roleId") Long roleId, @Param("authorityList") List<String> authorityList);

    /**
     * 指定角色 id、authority list，批量插入
     *
     * @param roleId        角色 id
     * @param authorityList authority list
     * @return int
     */
    int insertAllByRoleIdAndAuthorityList(@Param("roleId") Long roleId, @Param("authorityList") List<String> authorityList);

    /**
     * 指定角色 id，批量删除角色权限
     *
     * @param roleId 角色 id
     * @return int
     */
    int deleteAllByRoleId(@Param("roleId") Long roleId);


}
