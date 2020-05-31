package com.gioov.nimrod.user.service;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.user.entity.ApiEntity;
import com.gioov.tile.web.exception.BaseResponseException;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface ApiService {

    /**
     * 新增 API
     *
     * @param apiEntity ApiEntity
     * @return ApiEntity
     * @throws BaseResponseException BaseResponseException
     */
    ApiEntity addOne(ApiEntity apiEntity) throws BaseResponseException;

    /**
     * 保存 API
     *
     * @param apiEntity ApiEntity
     * @return ApiEntity
     */
    ApiEntity saveOne(ApiEntity apiEntity);

    /**
     * 指定 API id list，批量删除 API
     *
     * @param idList API id list
     * @return int 已删除 API 个数
     */
    int deleteAll(List<Long> idList);

    /**
     * 指定 API id，获取 API
     *
     * @param id API id
     * @return ApiEntity
     */
    ApiEntity getOne(Long id);

    /**
     * 指定 API 分类 id，分页获取所有 API
     *
     * @param page                页
     * @param rows                每页显示数量
     * @param apiCategoryId       API 分类 id
     * @param viewPageId          viewPageId
     * @param viewPageComponentId viewPageComponentId
     * @return Pagination<ApiEntity>
     */
    Pagination<ApiEntity> pageAllByApiCategoryId(Integer page, Integer rows, Long apiCategoryId, Long viewPageId, Long viewPageComponentId, Long roleId);

}
