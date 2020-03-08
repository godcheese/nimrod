package com.gioov.nimrod.user.service;

import com.gioov.nimrod.common.easyui.ComboTree;
import com.gioov.nimrod.user.entity.ViewPageCategoryEntity;
import com.gioov.tile.web.exception.BaseResponseException;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface ViewPageCategoryService {

    /**
     * 新增视图页面分类
     * @param viewPageCategoryEntity ViewPageCategoryEntity
     * @return ViewPageCategoryEntity
     */
    ViewPageCategoryEntity addOne(ViewPageCategoryEntity viewPageCategoryEntity);

    /**
     * 保存视图页面分类
     * @param viewPageCategoryEntity ViewPageCategoryEntity
     * @return ViewPageCategoryEntity
     */
    ViewPageCategoryEntity saveOne(ViewPageCategoryEntity viewPageCategoryEntity);

    /**
     * 指定视图页面分类 id list，批量删除视图页面分类
     * @param idList 视图页面分类 id list
     * @return int
     * @throws BaseResponseException BaseResponseException
     */
    int deleteAll(List<Long> idList) throws BaseResponseException;

    /**
     * 指定视图页面分类 id，获取所有视图页面分类
     * @param id 数据字典 id
     * @return ViewPageCategoryEntity
     */
    ViewPageCategoryEntity getOne(Long id);


    /**
     * 获取所有父级视图页面分类
     * @return List<ViewPageCategoryEntity>
     */
    List<ViewPageCategoryEntity> listAllParent();

    /**
     * 指定父级视图页面分类 id，获取所有视图页面分类
     * @param parentId 父级视图页面分类 id
     * @return List<ViewPageCategoryEntity>
     */
    List<ViewPageCategoryEntity> listAllByParentId(Long parentId);

    /**
     * 获取所有视图页面分类，以 ComboTree 形式展示
     * @return List<ComboTree>
     */
    List<ComboTree> listAllViewPageCategoryComboTree();
    /**
     * 指定父级视图页面分类 id，ViewPageCategoryComboTree list，获取所有子级视图页面分类
     * @param parentId 父级视图页面分类 id
     * @param viewPageCategoryComboTreeList ViewPageCategoryComboTree list
     * @return List<ComboTree>
     */
    List<ComboTree> getViewPageCategoryChildrenComboTree(long parentId, List<ComboTree> viewPageCategoryComboTreeList);
}
