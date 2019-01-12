package com.gioov.nimrod.system.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.common.mybatis.Pageable;
import com.gioov.nimrod.system.entity.ApiCategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("apiCategoryMapper")
@Mapper
public interface ApiCategoryMapper extends CrudMapper<ApiCategoryEntity, Long> {

    /**
     * 分页获取所有父级 id 为 null 的 API 分类
     *
     * @param pageable Pageable
     * @return List<ApiCategoryEntity>
     */
    List<ApiCategoryEntity> pageAllByParentIdIsNull(@Param("pageable") Pageable pageable);

    /**
     * 统计所有父级 id 为 null 的 API 分类个数
     *
     * @return int
     */
    int countAllByParentIdIsNull();

    /**
     * 指定 API 分类父级 id ，获取所有 API 分类
     *
     * @param parentId API 分类父级 id
     * @return List<ApiCategoryEntity>
     */
    List<ApiCategoryEntity> listAllByParentId(@Param("parentId") Long parentId);

    /**
     * 指定 API 分类父级 id ，获取 API 分类
     *
     * @param parentId API 分类父级 id
     * @return
     */
    ApiCategoryEntity getOneByParentId(@Param("parentId") Long parentId);

}
