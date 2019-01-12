package com.gioov.nimrod.system.service;

import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.entity.ViewPageEntity;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface ViewPageService {

    /**
     * 指定视图页面分类 id ，分页获取所有视图页面
     *
     * @param pageCategoryId 视图页面分类 id
     * @param page           页
     * @param rows           每页显示数量
     * @return Pagination.Result<ViewPageEntity>
     */
    Pagination.Result<ViewPageEntity> pageAllByPageCategoryId(Long pageCategoryId, Integer page, Integer rows);

    /**
     * 新增视图页面
     *
     * @param viewPageEntity ViewPageEntity
     * @return ViewPageEntity
     */
    ViewPageEntity insertOne(ViewPageEntity viewPageEntity) throws BaseResponseException;

    /**
     * 保存视图页面
     *
     * @param viewPageEntity ViewPageEntity
     * @return ViewPageEntity
     */
    ViewPageEntity updateOne(ViewPageEntity viewPageEntity) throws BaseResponseException;

    /**
     * 指定视图页面 id ，批量删除视图页面
     *
     * @param idList 视图页面 id list
     * @return 删除的视图页面个数
     */
    int deleteAll(List<Long> idList);

    /**
     * 指定视图页面 id ，获取视图页面信息
     *
     * @param id 视图页面 id
     * @return ViewPageEntity
     */
    ViewPageEntity getOne(Long id);

}
