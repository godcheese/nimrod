package com.gioov.nimrod.system.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.common.mybatis.Pageable;
import com.gioov.nimrod.system.entity.ApiEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component("apiMapper")
@Mapper
public interface ApiMapper extends CrudMapper<ApiEntity, Long> {

    /**
     * 指定 API 分类 id ，获取 API
     *
     * @param apiCategoryId API 分类 id
     * @return ApiEntity
     */
    ApiEntity getOneByApiCategoryId(@Param("apiCategoryId") Long apiCategoryId);

    /**
     * 指定 API 分类 id ，分页获取所有 API
     *
     * @param apiCategoryId API 分类 id
     * @param pageable      Pageable
     * @return List<ApiEntity>
     */
    List<ApiEntity> pageAllByApiCategoryId(@Param("apiCategoryId") Long apiCategoryId, @Param("pageable") Pageable pageable);

    /**
     * 指定 API 分类 id ，统计所有 API 个数
     *
     * @param apiCategoryId API 分类 id
     * @return int
     */
    int countAllByApiCategoryId(@Param("apiCategoryId") Long apiCategoryId);


    /**
     * 指定 authority ，获取 API
     *
     * @param authority API authority
     * @return ApiEntity
     */
    ApiEntity getOneByAuthority(@Param("authority") String authority);

}
