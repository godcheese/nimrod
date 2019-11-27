package com.gioov.nimrod.user.service;

import com.gioov.nimrod.common.easyui.ComboTree;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.user.entity.ApiCategoryEntity;
import com.gioov.tile.web.exception.BaseResponseException;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface ApiCategoryService {
    /**
     * 新增 API 分类
     * @param apiCategoryEntity ApiCategoryEntity
     * @return ApiCategoryEntity
     */
    ApiCategoryEntity addOne(ApiCategoryEntity apiCategoryEntity);

    /**
     * 保存 API 分类
     * @param apiCategoryEntity ApiCategoryEntity
     * @return ApiCategoryEntity
     */
    ApiCategoryEntity saveOne(ApiCategoryEntity apiCategoryEntity) throws BaseResponseException;

    /**
     * 指定 API 分类 id list，批量删除 API 分类
     * @param idList API 分类 id list
     * @return 已删除 API 分类个数
     * @throws BaseResponseException BaseResponseException
     */
    int deleteAll(List<Long> idList) throws BaseResponseException;

    /**
     * 指定 API 分类 id，获取所有 API 分类
     * @param id API 分类 id
     * @return ApiCategoryEntity
     */
    ApiCategoryEntity getOne(Long id);

    /**
     * 获取所有父级 API 分类
     * @return Pagination<ApiCategoryEntity>
     */
    List<ApiCategoryEntity> listAllParent();

    /**
     * 指定父级 API 分类 id，获取所有 API 分类
     * @param parentId API 分类父级 id
     * @return List<ApiCategoryEntity>
     */
    List<ApiCategoryEntity> listAllByParentId(Long parentId);

    /**
     * 获取所有 API 分类，以 ComboTree 形式展示
     * @return List<ComboTree>
     */
    List<ComboTree> listAllApiCategoryComboTree();
    /**
     * 指定父级 API 分类 id，ApiCategoryComboTree list，获取所有子级 API 分类
     * @param parentId 父级 API 分类 id
     * @param apiCategoryComboTreeList ApiCategoryComboTree list
     * @return List<ComboTree>
     */
    List<ComboTree> getApiCategoryChildrenComboTree(long parentId, List<ComboTree> apiCategoryComboTreeList);

}
