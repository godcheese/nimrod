package com.gioov.nimrod.user.mapper;

import com.gioov.nimrod.user.entity.ApiEntity;
import com.gioov.tile.mybatis.CrudMapper;
import com.github.pagehelper.Page;
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
     * 指定 API 分类 id，获取所有 API
     *
     * @param apiCategoryId API 分类 id
     * @return ApiEntity
     */
    ApiEntity getOneByApiCategoryId(@Param("apiCategoryId") Long apiCategoryId);

    /**
     * 指定 API 分类 id，分页获取所有 API
     *
     * @param apiCategoryId API 分类 id
     * @return List<ApiEntity>
     */
    Page<ApiEntity> pageAllByApiCategoryId(@Param("apiCategoryId") Long apiCategoryId);

    /**
     * 指定 authority，获取所有 API
     *
     * @param authority authority
     * @return ApiEntity
     */
    ApiEntity getOneByAuthority(@Param("authority") String authority);

    /**
     * 指定 API 分类 id list，分页获取所有 API
     *
     * @param apiCategoryIdList API 分类 id list
     * @return Page<ApiEntity>
     */
    Page<ApiEntity> pageAllByApiCategoryIdList(@Param("apiCategoryIdList") List<Long> apiCategoryIdList);

}
