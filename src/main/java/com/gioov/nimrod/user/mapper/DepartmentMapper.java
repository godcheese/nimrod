package com.gioov.nimrod.user.mapper;

import com.gioov.nimrod.user.entity.DepartmentEntity;
import com.gioov.tile.mybatis.CrudMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("departmentMapper")
@Mapper
public interface DepartmentMapper extends CrudMapper<DepartmentEntity, Long> {

    /**
     * 获取所有父级 id 为 null 的部门
     * @return List<DepartmentEntity>
     */
    List<DepartmentEntity> listAllParentIdIsNull();

    /**
     * 指定父级部门 id，获取所有部门
     * @param parentId 父级部门 id
     * @return List<ApiCategoryEntity>
     */
    List<DepartmentEntity> listAllByParentId(@Param("parentId") Long parentId);

    /**
     * 指定父级部门 id，获取部门
     * @param parentId 父级部门 id
     * @return DepartmentEntity
     */
    DepartmentEntity getOneByParentId(@Param("parentId") Long parentId);

}
