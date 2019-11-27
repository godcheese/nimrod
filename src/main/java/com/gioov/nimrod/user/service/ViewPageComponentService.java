package com.gioov.nimrod.user.service;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.user.entity.ViewPageComponentEntity;
import com.gioov.tile.web.exception.BaseResponseException;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface ViewPageComponentService {

    /**
     * 新增视图页面组件
     * @param viewPageComponentEntity ViewPageComponentEntity
     * @return ViewPageComponentEntity
     * @throws BaseResponseException BaseResponseException
     */
    ViewPageComponentEntity addOne(ViewPageComponentEntity viewPageComponentEntity) throws BaseResponseException;

    /**
     * 保存视图页面组件
     * @param viewPageComponentEntity ViewPageComponentEntity
     * @return ViewPageComponentEntity
     * @throws BaseResponseException BaseResponseException
     */
    ViewPageComponentEntity saveOne(ViewPageComponentEntity viewPageComponentEntity) throws BaseResponseException;

    /**
     * 指定视图页面组件 id，批量删除视图页面组件
     * @param idList 视图页面组件 id list
     * @return int
     */
    int deleteAll(List<Long> idList);

    /**
     * 指定视图页面组件 id，获取视图页面组件
     * @param id 视图页面组件 id
     * @return ViewPageComponentEntity
     */
    ViewPageComponentEntity getOne(Long id);

    /**
     * 指定视图页面 id，分页获取所有视图页面组件
     * @param page   页
     * @param rows   每页显示数量
     * @param viewPageId 视图页面 id
     * @return Pagination<ViewPageComponentEntity>
     */
    Pagination<ViewPageComponentEntity> pageAllByViewPageId(Integer page, Integer rows, Long viewPageId, Long roleId);

}
