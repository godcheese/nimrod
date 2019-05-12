package com.gioov.nimrod.system.service.impl;

import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.FailureMessage;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.entity.ApiCategoryEntity;
import com.gioov.nimrod.system.entity.ApiEntity;
import com.gioov.nimrod.system.mapper.ApiCategoryMapper;
import com.gioov.nimrod.system.mapper.ApiMapper;
import com.gioov.nimrod.system.service.ApiCategoryService;
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
public class ApiCategoryServiceImpl implements ApiCategoryService {

    @Autowired
    private ApiCategoryMapper apiCategoryMapper;

    @Autowired
    private ApiMapper apiMapper;

    @Override
    public Pagination<ApiCategoryEntity> pageAllByParentIdIsNull(Integer page, Integer rows) {
        Pagination<ApiCategoryEntity> pagination = new Pagination<>();
        List<ApiCategoryEntity> apiCategoryEntityList = apiCategoryMapper.pageAllByParentIdIsNull(new com.gioov.common.mybatis.Pageable(page, rows));
        if (apiCategoryEntityList != null) {
            pagination.setRows(apiCategoryEntityList);
        }
        pagination.setTotal(apiCategoryMapper.countAllByParentIdIsNull());
        return pagination;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ApiCategoryEntity insertOne(ApiCategoryEntity apiCategoryEntity) {
        Date date = new Date();
        ApiCategoryEntity apiCategoryEntity1 = new ApiCategoryEntity();
        apiCategoryEntity.setName(apiCategoryEntity.getName());
        if (apiCategoryEntity.getParentId() != null) {
            apiCategoryEntity.setParentId(apiCategoryEntity.getParentId());
        }
        apiCategoryEntity.setSort(apiCategoryEntity.getSort());
        apiCategoryEntity.setRemark(apiCategoryEntity.getRemark());
        apiCategoryEntity.setGmtModified(date);
        apiCategoryEntity.setGmtCreated(date);
        apiCategoryMapper.insertOne(apiCategoryEntity1);
        return apiCategoryEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ApiCategoryEntity updateOne(ApiCategoryEntity apiCategoryEntity) {
        ApiCategoryEntity apiCategoryEntity1 = apiCategoryMapper.getOne(apiCategoryEntity.getId());
        apiCategoryEntity1.setName(apiCategoryEntity.getName());
        apiCategoryEntity1.setSort(apiCategoryEntity.getSort());
        apiCategoryEntity1.setRemark(apiCategoryEntity.getRemark());
        apiCategoryEntity1.setGmtModified(new Date());
        apiCategoryMapper.updateOne(apiCategoryEntity1);
        return apiCategoryEntity1;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int deleteAll(List<Long> idList) throws BaseResponseException {
        int result = 0;
        for (Long id : idList) {
            ApiCategoryEntity viewPageCategoryEntity = apiCategoryMapper.getOneByParentId(id);
            if (viewPageCategoryEntity != null) {
                throw new BaseResponseException(FailureMessage.DELETE_API_CATEGORY_FAIL1);
            }
            ApiEntity apiEntity = apiMapper.getOneByApiCategoryId(id);
            if (apiEntity != null) {
                throw new BaseResponseException(FailureMessage.DELETE_API_CATEGORY_FAIL2);
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
    public List<ApiCategoryEntity> listAllByParentId(Long parentId) {
        return apiCategoryMapper.listAllByParentId(parentId);
    }

}
