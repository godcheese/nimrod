package com.gioov.nimrod.system.service.impl;

import com.gioov.common.mybatis.Pageable;
import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.FailureMessage;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.entity.ViewPageComponentEntity;
import com.gioov.nimrod.system.mapper.ViewPageComponentMapper;
import com.gioov.nimrod.system.service.ViewPageComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class ViewPageComponentServiceImpl implements ViewPageComponentService {

    @Autowired
    private ViewPageComponentMapper viewPageComponentMapper;

    @Override
    public Pagination.Result<ViewPageComponentEntity> pageAllByPageId(Long pageId, Integer page, Integer rows) {
        Pagination.Result<ViewPageComponentEntity> paginationResult = new Pagination().new Result<>();
        List<ViewPageComponentEntity> viewPageComponentEntityList = viewPageComponentMapper.pageAllByPageId(pageId, new Pageable(page, rows));
        if (viewPageComponentEntityList != null) {
            paginationResult.setRows(viewPageComponentEntityList);
        }
        paginationResult.setTotal(viewPageComponentMapper.countAllByPageId(pageId));
        return paginationResult;
    }

    @Override
    public ViewPageComponentEntity insertOne(ViewPageComponentEntity viewPageComponentEntity) throws BaseResponseException {
        ViewPageComponentEntity viewPageComponentEntity1 = new ViewPageComponentEntity();
        Date date = new Date();
        String authority = viewPageComponentEntity.getAuthority().toUpperCase();
        if (viewPageComponentMapper.getOneByAuthority(authority) != null) {
            throw new BaseResponseException(FailureMessage.ADD_API_AUTHORITY_FAIL);
        }
        viewPageComponentEntity1.setPageComponentType(viewPageComponentEntity.getPageComponentType());
        viewPageComponentEntity1.setName(viewPageComponentEntity.getName());
        viewPageComponentEntity1.setAuthority(viewPageComponentEntity.getAuthority());
        viewPageComponentEntity1.setPageId(viewPageComponentEntity.getPageId());
        viewPageComponentEntity1.setSort(viewPageComponentEntity.getSort());
        viewPageComponentEntity1.setRemark(viewPageComponentEntity.getRemark());
        viewPageComponentEntity1.setGmtModified(date);
        viewPageComponentEntity1.setGmtCreated(date);
        viewPageComponentMapper.insertOne(viewPageComponentEntity1);
        return viewPageComponentEntity1;
    }

    @Override
    public ViewPageComponentEntity updateOne(ViewPageComponentEntity viewPageComponentEntity) throws BaseResponseException {
        ViewPageComponentEntity viewPageComponentEntity1 = viewPageComponentMapper.getOne(viewPageComponentEntity.getId());
        Date date = new Date();
        String authority = viewPageComponentEntity.getAuthority().toUpperCase();
        ViewPageComponentEntity viewPageComponentEntity2 = viewPageComponentMapper.getOneByAuthority(authority);
        if (viewPageComponentEntity2 != null && !viewPageComponentEntity2.getId().equals(viewPageComponentEntity.getId())) {
            throw new BaseResponseException(FailureMessage.ADD_API_AUTHORITY_FAIL);
        }
        viewPageComponentEntity1.setPageComponentType(viewPageComponentEntity.getPageComponentType());
        viewPageComponentEntity1.setName(viewPageComponentEntity.getName());
        viewPageComponentEntity1.setAuthority(authority);
        viewPageComponentEntity1.setSort(viewPageComponentEntity.getSort());
        viewPageComponentEntity1.setRemark(viewPageComponentEntity.getRemark());
        viewPageComponentEntity1.setGmtModified(date);
        viewPageComponentMapper.updateOne(viewPageComponentEntity1);
        return viewPageComponentEntity1;
    }

    @Override
    public int deleteAll(List<Long> idList) {
        return viewPageComponentMapper.deleteAll(idList);
    }

    @Override
    public ViewPageComponentEntity getOne(Long id) {
        return viewPageComponentMapper.getOne(id);
    }
}
