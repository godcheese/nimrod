package com.gioov.nimrod.user.mapper;

import com.gioov.nimrod.user.entity.UserEntity;
import com.gioov.tile.mybatis.CrudMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("userMapper")
@Mapper
public interface UserMapper extends CrudMapper<UserEntity, Long> {
    /**
     * 指定 username 获取用户
     * @param username 用户名
     * @return UserEntity
     */
    UserEntity getOneByUsername(@Param("username") String username);

    /**
     * 指定电子邮箱，获取用户
     * @param email 电子邮箱
     * @return UserEntity
     */
    UserEntity getOneByEmail(@Param("email") String email);

    /**
     * 指定手机号码，获取用户
     * @param cellphone 手机号码
     * @return UserEntity
     */
    UserEntity getOneByCellphone(@Param("cellphone") String cellphone);

    /**
     * 伪删除用户，标记 gmtDeleted 字段
     * @param idList     id list
     * @param gmtDeleted 删除时间
     * @return int
     */
    int fakeDeleteAll(@Param("idList") List<Long> idList, @Param("gmtDeleted") Date gmtDeleted);

    /**
     * 撤销伪删除用户，不标记 gmtDeleted 字段
     * @param idList id list
     * @return int
     */
    int revokeFakeDeleteAll(@Param("idList") List<Long> idList);

    /**
     * 指定部门 id，分页获取所有用户
     * @param departmentId 部门 id
     * @return
     */
    Page<UserEntity> pageAllByDepartmentId(@Param("departmentId") Long departmentId);

    /**
     * 指定部门 id，获取用户
     * @param departmentId 部门 id
     * @return UserEntity
     */
    UserEntity getOneByDepartmentId(@Param("departmentId") Long departmentId);

    /**
     * 分页获取所有用户
     * @param userEntity UserEntity
     * @param gmtCreatedStart gmtCreatedStart
     * @param gmtCreatedEnd gmtCreatedEnd
     * @param gmtDeletedStart gmtDeletedStart
     * @param gmtDeletedEnd gmtDeletedEnd
     * @return Page<UserEntity>
     */
    Page<UserEntity> pageAll(@Param("userEntity") UserEntity userEntity, @Param("gmtCreatedStart") String gmtCreatedStart, @Param("gmtCreatedEnd") String gmtCreatedEnd, @Param("gmtDeletedStart") String gmtDeletedStart, @Param("gmtDeletedEnd") String gmtDeletedEnd);

}
