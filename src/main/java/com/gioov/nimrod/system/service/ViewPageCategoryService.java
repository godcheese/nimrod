package com.gioov.nimrod.system.service;

import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.entity.ViewPageCategoryEntity;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface ViewPageCategoryService {

    /**
     * 分页获取所有父级视图页面分类
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return Pagination.Result<ViewPageCategoryEntity>
     */
    Pagination.Result<ViewPageCategoryEntity> pageAllParent(Integer page, Integer rows);

    /**
     * 指定父级视图页面分类 id ，获取所有视图页面分类
     *
     * @param parentId 父级视图页面分类 id
     * @return List<ViewPageCategoryEntity>
     */
    List<ViewPageCategoryEntity> listAllByParentId(Long parentId);

    /**
     * 新增视图页面分类
     *
     * @param viewPageCategoryEntity ViewPageCategoryEntity
     * @return ViewPageCategoryEntity
     */
    ViewPageCategoryEntity insertOne(ViewPageCategoryEntity viewPageCategoryEntity);

    /**
     * 保存视图页面分类
     *
     * @param viewPageCategoryEntity ViewPageCategoryEntity
     * @return ViewPageCategoryEntity
     */
    ViewPageCategoryEntity updateOne(ViewPageCategoryEntity viewPageCategoryEntity);

    /**
     * 指定视图页面分类 id ，批量删除视图页面分类
     *
     * @param idList 视图页面分类 id list
     * @return 已删除视图页面分类个数
     */
    int deleteAll(List<Long> idList) throws BaseResponseException;

    /**
     * 指定视图页面分类 id ，获取视图页面分类信息
     *
     * @param id 数据字典 id
     * @return ViewPageCategoryEntity
     */
    ViewPageCategoryEntity getOne(Long id);

}
