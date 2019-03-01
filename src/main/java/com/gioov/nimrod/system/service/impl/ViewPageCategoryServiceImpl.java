package com.gioov.nimrod.system.service.impl;

import com.gioov.common.mybatis.Pageable;
import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.FailureMessage;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.entity.ViewPageCategoryEntity;
import com.gioov.nimrod.system.entity.ViewPageEntity;
import com.gioov.nimrod.system.mapper.ViewPageCategoryMapper;
import com.gioov.nimrod.system.mapper.ViewPageMapper;
import com.gioov.nimrod.system.service.ViewPageCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class ViewPageCategoryServiceImpl implements ViewPageCategoryService {

    @Autowired
    private ViewPageCategoryMapper viewPageCategoryMapper;

    @Autowired
    private ViewPageMapper viewPageMapper;


    @Override
    public Pagination.Result<ViewPageCategoryEntity> pageAllParent(Integer page, Integer rows) {
        Pagination.Result<ViewPageCategoryEntity> paginationResult = new Pagination().new Result<>();
        List<ViewPageCategoryEntity> viewPageCategoryEntityList = viewPageCategoryMapper.pageAllByParentIdIsNull(new Pageable(page, rows));
        if (viewPageCategoryEntityList != null) {
            paginationResult.setRows(viewPageCategoryEntityList);
        }
        paginationResult.setTotal(viewPageCategoryMapper.countAllByParentIdIsNull());
        return paginationResult;
    }

    @Override
    public List<ViewPageCategoryEntity> listAllByParentId(Long parentId) {
        return viewPageCategoryMapper.listAllByParentId(parentId);
    }

    @Override
    public ViewPageCategoryEntity insertOne(ViewPageCategoryEntity viewPageCategoryEntity) {
        ViewPageCategoryEntity viewPageCategoryEntity1 = new ViewPageCategoryEntity();
        Date date = new Date();
        viewPageCategoryEntity1.setName(viewPageCategoryEntity.getName());
        viewPageCategoryEntity1.setParentId(viewPageCategoryEntity.getParentId());
        viewPageCategoryEntity1.setSort(viewPageCategoryEntity.getSort());
        viewPageCategoryEntity1.setRemark(viewPageCategoryEntity.getRemark());
        viewPageCategoryEntity1.setGmtModified(date);
        viewPageCategoryEntity1.setGmtCreated(date);
        viewPageCategoryMapper.insertOne(viewPageCategoryEntity1);
        return viewPageCategoryEntity1;
    }

    @Override
    public ViewPageCategoryEntity updateOne(ViewPageCategoryEntity viewPageCategoryEntity) {
        ViewPageCategoryEntity viewPageCategoryEntity1 = viewPageCategoryMapper.getOne(viewPageCategoryEntity.getId());
        Date date = new Date();
        viewPageCategoryEntity1.setName(viewPageCategoryEntity.getName());
        viewPageCategoryEntity1.setSort(viewPageCategoryEntity.getSort());
        viewPageCategoryEntity1.setRemark(viewPageCategoryEntity.getRemark());
        viewPageCategoryEntity1.setGmtModified(date);
        viewPageCategoryMapper.updateOne(viewPageCategoryEntity);
        return viewPageCategoryEntity1;
    }

    @Override
    public int deleteAll(List<Long> idList) throws BaseResponseException {
        int result = 0;
        for (Long id : idList) {
            ViewPageCategoryEntity viewPageCategoryEntity = viewPageCategoryMapper.getOneByParentId(id);
            if (viewPageCategoryEntity != null) {
                throw new BaseResponseException(FailureMessage.DELETE_VIEW_PAGE_CATEGORY_FAIL1);
            }
            ViewPageEntity viewPageEntity = viewPageMapper.getOneByPageCategoryId(id);
            if (viewPageEntity != null) {
                throw new BaseResponseException(FailureMessage.DELETE_VIEW_PAGE_CATEGORY_FAIL2);
            }
            viewPageCategoryMapper.deleteOne(id);
            result++;
        }
        return result;
    }

    @Override
    public ViewPageCategoryEntity getOne(Long id) {
        return viewPageCategoryMapper.getOne(id);
    }
}
