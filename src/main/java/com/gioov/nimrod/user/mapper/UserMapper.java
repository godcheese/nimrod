package com.gioov.nimrod.user.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.common.mybatis.Pageable;
import com.gioov.nimrod.user.entity.UserEntity;
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
     *
     * @param username 用户名
     * @return UserEntity
     */
    UserEntity getOneByUsername(@Param("username") String username);

    UserEntity getOneByEmail(@Param("email") String email);

    UserEntity getOneByCellphone(@Param("cellphone") String cellphone);

    /**
     * 伪删除用户，标记 gmtDeleted 字段
     *
     * @param idList     id list
     * @param gmtDeleted 删除时间
     * @return int
     */
    int fakeDeleteAll(@Param("idList") List<Long> idList, @Param("gmtDeleted") Date gmtDeleted);

    /**
     * 撤销伪删除用户，不标记 gmtDeleted 字段
     *
     * @param idList id list
     * @return int
     */
    int revokeFakeDeleteAll(@Param("idList") List<Long> idList);

    List<UserEntity> pageAllByDepartmentId(@Param("departmentId") Long departmentId, @Param("pageable") Pageable pageable);

    int countAllByDepartmentId(@Param("departmentId") Long departmentId);

    UserEntity getOneByDepartmentId(@Param("departmentId") Long departmentId);

}
