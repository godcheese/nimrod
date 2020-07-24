package com.godcheese.nimrod.user.service.impl;

import com.godcheese.nimrod.common.easyui.ComboTree;
import com.godcheese.nimrod.common.easyui.EasyUi;
import com.godcheese.nimrod.common.others.FailureEntity;
import com.godcheese.nimrod.user.entity.ApiCategoryEntity;
import com.godcheese.nimrod.user.entity.ApiEntity;
import com.godcheese.nimrod.user.mapper.ApiCategoryMapper;
import com.godcheese.nimrod.user.mapper.ApiMapper;
import com.godcheese.nimrod.user.service.ApiCategoryService;
import com.godcheese.tile.web.exception.BaseResponseException;
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
public class ApiCategoryServiceImpl implements ApiCategoryService {

    @Autowired
    private ApiCategoryMapper apiCategoryMapper;

    @Autowired
    private ApiMapper apiMapper;
    @Autowired
    private FailureEntity failureEntity;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ApiCategoryEntity addOne(ApiCategoryEntity apiCategoryEntity) {
        Date date = new Date();
        apiCategoryEntity.setGmtModified(date);
        apiCategoryEntity.setGmtCreated(date);
        apiCategoryMapper.insertOne(apiCategoryEntity);
        return apiCategoryEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ApiCategoryEntity saveOne(ApiCategoryEntity apiCategoryEntity) throws BaseResponseException {
        if (apiCategoryEntity.getId().equals(apiCategoryEntity.getParentId())) {
            throw new BaseResponseException(failureEntity.i18n("api_category.save_fail_do_not_save_self_api_category"));
        }
        apiCategoryEntity.setGmtModified(new Date());
        apiCategoryMapper.updateOne(apiCategoryEntity);
        return apiCategoryEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int deleteAll(List<Long> idList) throws BaseResponseException {
        int result = 0;
        for (Long id : idList) {
            ApiCategoryEntity viewPageCategoryEntity = apiCategoryMapper.getOneByParentId(id);
            if (viewPageCategoryEntity != null) {
                throw new BaseResponseException(failureEntity.i18n("api_category.delete_fail_has_category"));
            }
            ApiEntity apiEntity = apiMapper.getOneByApiCategoryId(id);
            if (apiEntity != null) {
                throw new BaseResponseException(failureEntity.i18n("api_category.delete_fail_has_api"));
            }
            apiCategoryMapper.deleteOne(id);
            result++;
        }
        return result;
    }

    @Override
    public ApiCategoryEntity getOne(Long id) {
        return apiCategoryMapper.getOne(id);
    }

    @Override
    public List<ApiCategoryEntity> listAllParent() {
        List<ApiCategoryEntity> apiCategoryEntityList = apiCategoryMapper.listAllByParentIdIsNull();
        List<ApiCategoryEntity> apiCategoryEntityListResult = new ArrayList<>();
        for (ApiCategoryEntity apiCategoryEntity : apiCategoryEntityList) {
            if (apiCategoryMapper.getOneByParentId(apiCategoryEntity.getId()) != null) {
                apiCategoryEntity.setState(EasyUi.State.CLOSED);
            }
            apiCategoryEntityListResult.add(apiCategoryEntity);
        }
        return apiCategoryEntityListResult;
    }

    @Override
    public List<ApiCategoryEntity> listAllByParentId(Long parentId) {
        List<ApiCategoryEntity> apiCategoryEntityList = apiCategoryMapper.listAllByParentId(parentId);
        List<ApiCategoryEntity> apiCategoryEntityListResult = new ArrayList<>();
        for (ApiCategoryEntity apiCategoryEntity : apiCategoryEntityList) {
            if (apiCategoryMapper.getOneByParentId(apiCategoryEntity.getId()) != null) {
                apiCategoryEntity.setState(EasyUi.State.CLOSED);
            }
            apiCategoryEntityListResult.add(apiCategoryEntity);
        }
        return apiCategoryEntityListResult;
    }

    @Override
    public List<ComboTree> listAllApiCategoryComboTree() {
        List<ComboTree> comboTreeList = new ArrayList<>(0);
        List<ApiCategoryEntity> apiCategoryEntityList = apiCategoryMapper.listAll();
        for (ApiCategoryEntity apiCategoryEntity : apiCategoryEntityList) {
            ComboTree comboTree = new ComboTree();
            comboTree.setId(apiCategoryEntity.getId());
            comboTree.setText(apiCategoryEntity.getName());
            comboTree.setParentId(apiCategoryEntity.getParentId());
            comboTreeList.add(comboTree);
        }
        return comboTreeList;
    }

    @Override
    public List<ComboTree> getApiCategoryChildrenComboTree(long parentId, List<ComboTree> apiCategoryComboTreeList) {
        List<ComboTree> children = new ArrayList<>(0);
        for (ComboTree comboTree : apiCategoryComboTreeList) {
            if (comboTree.getParentId() != null && comboTree.getParentId().equals(parentId)) {
                children.add(comboTree);
            }
        }
        for (ComboTree child : children) {
            List<ComboTree> childChildren = getApiCategoryChildrenComboTree(child.getId(), apiCategoryComboTreeList);
            child.setChildren(childChildren);
        }
        if (children.size() == 0) {
            return null;
        }
        return children;
    }

}
