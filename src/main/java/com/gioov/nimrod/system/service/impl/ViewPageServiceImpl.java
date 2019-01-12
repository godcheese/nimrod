package com.gioov.nimrod.system.service.impl;

import com.gioov.common.mybatis.Pageable;
import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.constant.FailureMessage;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.entity.ViewPageEntity;
import com.gioov.nimrod.system.mapper.ViewPageMapper;
import com.gioov.nimrod.system.service.ViewPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class ViewPageServiceImpl implements ViewPageService {

    @Autowired
    private ViewPageMapper viewPageMapper;

    @Override
    public Pagination.Result<ViewPageEntity> pageAllByPageCategoryId(Long pageCategoryId, Integer page, Integer rows) {
        List<ViewPageEntity> viewPageEntityList;
        Pagination.Result<ViewPageEntity> paginationResult = new Pagination().new Result<>();
        viewPageEntityList = viewPageMapper.pageAllByPageCategoryId(pageCategoryId, new Pageable(page, rows));
        if (viewPageEntityList != null) {
            paginationResult.setRows(viewPageEntityList);
        }
        int count = viewPageMapper.countAllByPageCategoryId(pageCategoryId);
        paginationResult.setTotal(count);
        return paginationResult;
    }

    @Override
    public ViewPageEntity insertOne(ViewPageEntity viewPageEntity) throws BaseResponseException {
        ViewPageEntity viewPageEntity1 = new ViewPageEntity();
        Date date = new Date();

        String authority = viewPageEntity.getAuthority().toUpperCase();
        viewPageEntity = viewPageMapper.getOneByAuthority(authority);
        if (viewPageEntity != null) {
            throw new BaseResponseException(FailureMessage.ADD_VIEW_PAGE_AUTHORITY_FAIL);
        }
        viewPageEntity = new ViewPageEntity();
        viewPageEntity1.setName(viewPageEntity.getName());
        viewPageEntity1.setUrl(viewPageEntity.getUrl());
        viewPageEntity1.setAuthority(authority);
        viewPageEntity1.setPageCategoryId(viewPageEntity.getPageCategoryId());
        viewPageEntity1.setSort(viewPageEntity.getSort());
        viewPageEntity1.setRemark(viewPageEntity.getRemark());
        viewPageEntity1.setGmtModified(date);
        viewPageEntity1.setGmtCreated(date);
        viewPageMapper.insertOne(viewPageEntity1);
        return viewPageEntity1;
    }

    @Override
    public ViewPageEntity updateOne(ViewPageEntity viewPageEntity) throws BaseResponseException {
        ViewPageEntity viewPageEntity1 = viewPageMapper.getOne(viewPageEntity.getId());
        Date date = new Date();
        String authority = viewPageEntity.getAuthority().toUpperCase();
        ViewPageEntity viewPageEntity2 = viewPageMapper.getOneByAuthority(authority);
        if (viewPageEntity2 != null && !viewPageEntity2.getId().equals(viewPageEntity.getId())) {
            throw new BaseResponseException(FailureMessage.ADD_VIEW_PAGE_AUTHORITY_FAIL);
        }
        viewPageEntity1.setName(viewPageEntity.getName());
        viewPageEntity1.setUrl(viewPageEntity.getUrl());
        viewPageEntity1.setAuthority(authority);
        viewPageEntity1.setSort(viewPageEntity.getSort());
        viewPageEntity1.setRemark(viewPageEntity.getRemark());
        viewPageEntity1.setGmtModified(date);
        viewPageMapper.updateOne(viewPageEntity1);
        return viewPageEntity1;
    }

    @Override
    public int deleteAll(List<Long> idList) {
        return viewPageMapper.deleteAll(idList);
    }

    @Override
    public ViewPageEntity getOne(Long id) {
        return viewPageMapper.getOne(id);
    }
}
