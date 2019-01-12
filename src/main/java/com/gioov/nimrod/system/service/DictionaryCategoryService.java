package com.gioov.nimrod.system.service;

import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.entity.DictionaryCategoryEntity;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface DictionaryCategoryService {

    /**
     * 分页获取所有父级数据字典分类
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return Pagination.Result<ApiEntity>
     */
    Pagination.Result<DictionaryCategoryEntity> pageAllParent(Integer page, Integer rows);

    /**
     * 指定父级数据字典分类 id ，获取所有数据字典分类
     *
     * @param parentId 父级数据字典分类 id
     * @return Pagination.Result<ApiEntity>
     */
    List<DictionaryCategoryEntity> listAllByParentId(Long parentId);

    /**
     * 新增数据字典分类
     *
     * @param dictionaryCategoryEntity DictionaryCategoryEntity
     * @return DictionaryCategoryEntity
     */
    DictionaryCategoryEntity insertOne(DictionaryCategoryEntity dictionaryCategoryEntity);

    /**
     * 保存数据字典分类
     *
     * @param dictionaryCategoryEntity DictionaryCategoryEntity
     * @return DictionaryCategoryEntity
     */
    DictionaryCategoryEntity updateOne(DictionaryCategoryEntity dictionaryCategoryEntity);

    /**
     * 指定数据字典分类 id ，批量删除数据字典分类
     *
     * @param idList 数据字典分类 id list
     * @return 已删除数据字典分类个数
     */
    int deleteAll(List<Long> idList) throws BaseResponseException;

    /**
     * 指定数据字典分类 id ，获取数据字典分类信息
     *
     * @param id 数据字典分类 id
     * @return DictionaryCategoryEntity
     */
    DictionaryCategoryEntity getOne(Long id);
}
