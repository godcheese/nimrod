package com.godcheese.nimrod.user.service.impl;

import com.godcheese.nimrod.common.easyui.Pagination;
import com.godcheese.nimrod.common.others.FailureEntity;
import com.godcheese.nimrod.system.service.DictionaryService;
import com.godcheese.nimrod.user.entity.ApiEntity;
import com.godcheese.nimrod.user.mapper.ApiMapper;
import com.godcheese.nimrod.user.mapper.RoleAuthorityMapper;
import com.godcheese.nimrod.user.mapper.ViewPageApiMapper;
import com.godcheese.nimrod.user.mapper.ViewPageComponentApiMapper;
import com.godcheese.nimrod.user.service.ApiService;
import com.godcheese.tile.web.exception.BaseResponseException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
public class ApiServiceImpl implements ApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Autowired
    private ApiMapper apiMapper;
    @Autowired
    private FailureEntity failureEntity;
    @Autowired
    private ViewPageComponentApiMapper viewPageComponentApiMapper;
    @Autowired
    private ViewPageApiMapper viewPageApiMapper;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ApiEntity addOne(ApiEntity apiEntity) throws BaseResponseException {
        Date date = new Date();
        String authority = apiEntity.getAuthority().toUpperCase();
        if (apiMapper.getOneByAuthority(authority) != null) {
            throw new BaseResponseException(failureEntity.i18n("api.add_fail_authority_exists"));
        }
        apiEntity.setAuthority(authority);
        apiEntity.setGmtModified(date);
        apiEntity.setGmtCreated(date);
        apiMapper.insertOne(apiEntity);
        return apiEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ApiEntity saveOne(ApiEntity apiEntity) {
        ApiEntity apiEntity1 = apiMapper.getOne(apiEntity.getId());
        apiEntity1.setName(apiEntity.getName());
        apiEntity1.setUrl(apiEntity.getUrl());
        apiEntity1.setAuthority(apiEntity.getAuthority().toUpperCase());
        apiEntity1.setApiCategoryId(apiEntity.getApiCategoryId());
        apiEntity1.setSort(apiEntity.getSort());
        apiEntity1.setRemark(apiEntity.getRemark());
        apiEntity1.setGmtModified(new Date());
        apiMapper.updateOne(apiEntity1);
        return apiEntity1;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int deleteAll(List<Long> idList) {
        return apiMapper.deleteAll(idList);
    }

    @Override
    public ApiEntity getOne(Long id) {
        return apiMapper.getOne(id);
    }

    @Override
    public Pagination<ApiEntity> pageAllByApiCategoryId(Integer page, Integer rows, Long apiCategoryId, Long viewPageId, Long viewPageComponentId, Long roleId) {
        Pagination<ApiEntity> pagination = new Pagination<>();
        PageHelper.startPage(page, rows);
        Page<ApiEntity> apiEntityPage = apiMapper.pageAllByApiCategoryId(apiCategoryId);
        List<ApiEntity> apiEntityList = apiEntityPage.getResult();
        List<ApiEntity> apiEntityListResult = new ArrayList<>();
        Integer isOrNotIs = Integer.valueOf((String) dictionaryService.get("IS_OR_NOT", "IS"));
        Integer isOrNotNot = Integer.valueOf((String) dictionaryService.get("IS_OR_NOT", "NOT"));
        if (viewPageId != null) {
            for (ApiEntity apiEntity : apiEntityList) {
                if (viewPageApiMapper.getOneByViewPageIdAndApiId(viewPageId, apiEntity.getId()) != null) {
                    // 是否已关联，IS=是，NOT=否
                    apiEntity.setIsAssociated(isOrNotIs);
                } else {
                    apiEntity.setIsAssociated(isOrNotNot);
                }
                apiEntityListResult.add(apiEntity);
            }
            pagination.setRows(apiEntityListResult);
        } else if (viewPageComponentId != null) {
            for (ApiEntity apiEntity : apiEntityList) {
                if (viewPageComponentApiMapper.getOneByViewPageComponentIdAndApiId(viewPageComponentId, apiEntity.getId()) != null) {
                    // 是否已关联，IS=是，NOT=否
                    apiEntity.setIsAssociated(isOrNotIs);
                } else {
                    apiEntity.setIsAssociated(isOrNotNot);
                }
                apiEntityListResult.add(apiEntity);
            }
            pagination.setRows(apiEntityListResult);
        } else if (roleId != null) {
            for (ApiEntity apiEntity : apiEntityList) {
                if (roleAuthorityMapper.getOneByRoleIdAndAuthority(roleId, apiEntity.getAuthority()) != null) {
                    // 是否已授权，IS=是，NOT=否
                    apiEntity.setIsGranted(isOrNotIs);
                } else {
                    apiEntity.setIsGranted(isOrNotNot);
                }
                apiEntityListResult.add(apiEntity);
            }
            pagination.setRows(apiEntityListResult);
        } else {
            pagination.setRows(apiEntityList);
        }
        pagination.setTotal(apiEntityPage.getTotal());
        return pagination;
    }
}
