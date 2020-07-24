package com.godcheese.nimrod.system.service;

import com.godcheese.nimrod.common.easyui.ComboTree;
import com.godcheese.nimrod.system.entity.DictionaryCategoryEntity;
import com.godcheese.tile.web.exception.BaseResponseException;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface DictionaryCategoryService {

    /**
     * 分页获取所有父级数据字典分类
     *
     * @return List<DictionaryCategoryEntity>
     */
    List<DictionaryCategoryEntity> listAllParent();

    /**
     * 指定父级数据字典分类 id，获取所有数据字典分类
     *
     * @param parentId 父级数据字典分类 id
     * @return List<DictionaryEntity>
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
    DictionaryCategoryEntity updateOne(DictionaryCategoryEntity dictionaryCategoryEntity) throws BaseResponseException;

    /**
     * 指定数据字典分类 id list，批量删除数据字典分类
     *
     * @param idList 数据字典分类 id list
     * @return int
     * @throws BaseResponseException BaseResponseException
     */
    int deleteAll(List<Long> idList) throws BaseResponseException;

    /**
     * 指定数据字典分类 id，获取数据字典分类
     *
     * @param id 数据字典分类 id
     * @return DictionaryCategoryEntity
     */
    DictionaryCategoryEntity getOne(Long id);

    /**
     * 获取所有数据字典分类，以 ComboTree 形式展示
     *
     * @return List<ComboTree>
     */
    List<ComboTree> listAllDictionaryCategoryComboTree();

    /**
     * 指定父级数据字典分类 id，DictionaryCategoryComboTree list，获取所有子级数据字典分类
     *
     * @param parentId                        父级数据字典分类 id
     * @param dictionaryCategoryComboTreeList DictionaryCategoryComboTree list
     * @return List<ComboTree>
     */
    List<ComboTree> getDictionaryCategoryChildrenComboTree(long parentId, List<ComboTree> dictionaryCategoryComboTreeList);
}
