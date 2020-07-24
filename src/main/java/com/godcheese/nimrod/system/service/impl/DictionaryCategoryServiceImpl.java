package com.godcheese.nimrod.system.service.impl;

import com.godcheese.nimrod.common.easyui.ComboTree;
import com.godcheese.nimrod.common.easyui.EasyUi;
import com.godcheese.nimrod.common.others.FailureEntity;
import com.godcheese.nimrod.system.entity.DictionaryCategoryEntity;
import com.godcheese.nimrod.system.entity.DictionaryEntity;
import com.godcheese.nimrod.system.mapper.DictionaryCategoryMapper;
import com.godcheese.nimrod.system.mapper.DictionaryMapper;
import com.godcheese.nimrod.system.service.DictionaryCategoryService;
import com.godcheese.tile.web.exception.BaseResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class DictionaryCategoryServiceImpl implements DictionaryCategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryCategoryServiceImpl.class);

    @Autowired
    private DictionaryCategoryMapper dictionaryCategoryMapper;

    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Autowired
    private FailureEntity failureEntity;

    @Override
    public List<DictionaryCategoryEntity> listAllParent() {
        List<DictionaryCategoryEntity> dictionaryCategoryEntityList = dictionaryCategoryMapper.listAllByParentIdIsNull();
        List<DictionaryCategoryEntity> dictionaryCategoryEntityListResult = new ArrayList<>();
        for (DictionaryCategoryEntity dictionaryCategoryEntity : dictionaryCategoryEntityList) {
            if (dictionaryCategoryMapper.getOneByParentId(dictionaryCategoryEntity.getId()) != null) {
                dictionaryCategoryEntity.setState(EasyUi.State.CLOSED);
            }
            dictionaryCategoryEntityListResult.add(dictionaryCategoryEntity);
        }
        return dictionaryCategoryEntityListResult;
    }

    @Override
    public List<DictionaryCategoryEntity> listAllByParentId(Long parentId) {
        List<DictionaryCategoryEntity> dictionaryCategoryEntityList = dictionaryCategoryMapper.listAllByParentId(parentId);
        List<DictionaryCategoryEntity> dictionaryCategoryEntityListResult = new ArrayList<>();
        for (DictionaryCategoryEntity dictionaryCategoryEntity : dictionaryCategoryEntityList) {
            if (dictionaryCategoryMapper.getOneByParentId(dictionaryCategoryEntity.getId()) != null) {
                dictionaryCategoryEntity.setState(EasyUi.State.CLOSED);
            }
            dictionaryCategoryEntityListResult.add(dictionaryCategoryEntity);
        }
        return dictionaryCategoryEntityListResult;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DictionaryCategoryEntity insertOne(DictionaryCategoryEntity dictionaryCategoryEntity) {
        Date date = new Date();
        dictionaryCategoryEntity.setGmtCreated(date);
        dictionaryCategoryMapper.insertOne(dictionaryCategoryEntity);
        return dictionaryCategoryEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DictionaryCategoryEntity updateOne(DictionaryCategoryEntity dictionaryCategoryEntity) throws BaseResponseException {
        if (dictionaryCategoryEntity.getId().equals(dictionaryCategoryEntity.getParentId())) {
            throw new BaseResponseException(failureEntity.i18n("dictionary_category.save_fail_do_not_save_self_dictionary_category"));
        }
        dictionaryCategoryEntity.setGmtModified(new Date());
        dictionaryCategoryMapper.updateOne(dictionaryCategoryEntity);
        return dictionaryCategoryEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int deleteAll(List<Long> idList) throws BaseResponseException {
        int result = 0;
        for (Long id : idList) {
            DictionaryCategoryEntity dictionaryCategoryEntity = dictionaryCategoryMapper.getOneByParentId(id);
            if (dictionaryCategoryEntity != null) {
                throw new BaseResponseException(failureEntity.i18n("dictionary_category.delete_fail_has_children_category"));
            }
            DictionaryEntity dictionaryEntity = dictionaryMapper.getOneByDictionaryCategoryId(id);
            if (dictionaryEntity != null) {
                throw new BaseResponseException(failureEntity.i18n("dictionary_category.delete_fail_has_dictionary"));
            }
            dictionaryCategoryMapper.deleteOne(id);
            result++;
        }
        return result;
    }

    @Override
    public DictionaryCategoryEntity getOne(Long id) {
        return dictionaryCategoryMapper.getOne(id);
    }

    @Override
    public List<ComboTree> listAllDictionaryCategoryComboTree() {
        List<ComboTree> comboTreeList = new ArrayList<>(0);
        List<DictionaryCategoryEntity> dictionaryCategoryEntityList = dictionaryCategoryMapper.listAll();
        for (DictionaryCategoryEntity dictionaryCategoryEntity : dictionaryCategoryEntityList) {
            ComboTree comboTree = new ComboTree();
            comboTree.setId(dictionaryCategoryEntity.getId());
            comboTree.setText(dictionaryCategoryEntity.getName());
            comboTree.setParentId(dictionaryCategoryEntity.getParentId());
            comboTreeList.add(comboTree);
        }
        return comboTreeList;
    }

    @Override
    public List<ComboTree> getDictionaryCategoryChildrenComboTree(long parentId, List<ComboTree> dictionaryCategoryComboTreeList) {
        List<ComboTree> children = new ArrayList<>(0);
        for (ComboTree comboTree : dictionaryCategoryComboTreeList) {
            if (comboTree.getParentId() != null && comboTree.getParentId().equals(parentId)) {
                children.add(comboTree);
            }
        }
        for (ComboTree child : children) {
            List<ComboTree> childChildren = getDictionaryCategoryChildrenComboTree(child.getId(), dictionaryCategoryComboTreeList);
            child.setChildren(childChildren);
        }
        if (children.size() == 0) {
            return null;
        }
        return children;
    }
}
