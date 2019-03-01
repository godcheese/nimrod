package com.gioov.nimrod.system.service.impl;

import com.gioov.common.mybatis.Pageable;
import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.FailureMessage;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.entity.ApiEntity;
import com.gioov.nimrod.system.mapper.ApiMapper;
import com.gioov.nimrod.system.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiMapper apiMapper;

    @Override
    public Pagination.Result<ApiEntity> pageAllByApiCategoryId(Long apiCategoryId, Integer page, Integer rows) {
        Pagination.Result<ApiEntity> paginationResult = new Pagination().new Result<>();
        List<ApiEntity> apiEntityList = apiMapper.pageAllByApiCategoryId(apiCategoryId, new Pageable(page, rows));
        if (apiEntityList != null) {
            paginationResult.setRows(apiEntityList);
        }
        paginationResult.setTotal(apiMapper.countAllByApiCategoryId(apiCategoryId));
        return paginationResult;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ApiEntity insertOne(ApiEntity apiEntity) throws BaseResponseException {
        ApiEntity apiEntity1 = new ApiEntity();
        Date date = new Date();
        String authority = apiEntity.getAuthority().toUpperCase();
        if (apiMapper.getOneByAuthority(authority) != null) {
            throw new BaseResponseException(FailureMessage.ADD_API_AUTHORITY_FAIL);
        }
        apiEntity1.setName(apiEntity.getName());
        apiEntity1.setUrl(apiEntity.getName());
        apiEntity1.setAuthority(authority);
        apiEntity1.setApiCategoryId(apiEntity.getApiCategoryId());
        apiEntity1.setSort(apiEntity.getSort());
        apiEntity1.setRemark(apiEntity.getRemark());
        apiEntity1.setGmtModified(date);
        apiEntity1.setGmtCreated(date);
        apiMapper.insertOne(apiEntity1);
        return apiEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ApiEntity updateOne(ApiEntity apiEntity) {
        ApiEntity apiEntity1 = apiMapper.getOne(apiEntity.getId());
        apiEntity1.setName(apiEntity.getName());
        apiEntity1.setUrl(apiEntity.getUrl());
        apiEntity1.setAuthority(apiEntity.getAuthority().toUpperCase());
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

}
