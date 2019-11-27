package com.gioov.nimrod.user.service;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.user.entity.ViewPageEntity;
import com.gioov.tile.web.exception.BaseResponseException;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface ViewPageService {
    /**
     * 新增视图页面
     * @param viewPageEntity ViewPageEntity
     * @return ViewPageEntity
     * @throws BaseResponseException BaseResponseException
     */
    ViewPageEntity addOne(ViewPageEntity viewPageEntity) throws BaseResponseException;

    /**
     * 保存视图页面
     * @param viewPageEntity ViewPageEntity
     * @return ViewPageEntity
     * @throws BaseResponseException BaseResponseException
     */
    ViewPageEntity saveOne(ViewPageEntity viewPageEntity) throws BaseResponseException;

    /**
     * 指定视图页面 id，批量删除视图页面
     * @param idList 视图页面 id list
     * @return int
     */
    int deleteAll(List<Long> idList);

    /**
     * 指定视图页面 id，获取视图页面
     * @param id 视图页面 id
     * @return ViewPageEntity
     */
    ViewPageEntity getOne(Long id);

    /**
     * 指定视图页面分类 id，分页获取所有视图页面
     * @param viewPageCategoryId 视图页面分类 id
     * @param page 页
     * @param rows 每页显示数量
     * @return Pagination<ViewPageEntity>
     */
    Pagination<ViewPageEntity> pageAllByViewPageCategoryId(Integer page, Integer rows, Long viewPageCategoryId, Long roleId);

}
