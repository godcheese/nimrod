package com.gioov.nimrod.user.service.impl;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.others.FailureEntity;
import com.gioov.nimrod.system.service.DictionaryService;
import com.gioov.nimrod.user.entity.ViewPageEntity;
import com.gioov.nimrod.user.mapper.RoleAuthorityMapper;
import com.gioov.nimrod.user.mapper.ViewPageMapper;
import com.gioov.nimrod.user.service.ViewPageService;
import com.gioov.tile.web.exception.BaseResponseException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private FailureEntity failureEntity;
    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;
    @Autowired
    private DictionaryService dictionaryService;

    @Override
    public ViewPageEntity addOne(ViewPageEntity viewPageEntity) throws BaseResponseException {
        Date date = new Date();
        String authority = viewPageEntity.getAuthority().toUpperCase();
        ViewPageEntity viewPageEntity2 = viewPageMapper.getOneByAuthority(authority);
        if (viewPageEntity2 != null) {
            throw new BaseResponseException(failureEntity.i18n("view_page.add_fail_authority_exists"));
        }
        viewPageEntity.setAuthority(authority);
        viewPageEntity.setGmtModified(date);
        viewPageEntity.setGmtCreated(date);
        viewPageMapper.insertOne(viewPageEntity);
        return viewPageEntity;
    }

    @Override
    public ViewPageEntity saveOne(ViewPageEntity viewPageEntity) throws BaseResponseException {
        ViewPageEntity viewPageEntity1 = viewPageMapper.getOne(viewPageEntity.getId());
        Date date = new Date();
        String authority = viewPageEntity.getAuthority().toUpperCase();
        ViewPageEntity viewPageEntity2 = viewPageMapper.getOneByAuthority(authority);
        if (viewPageEntity2 != null && !viewPageEntity2.getId().equals(viewPageEntity.getId())) {
            throw new BaseResponseException(failureEntity.i18n("view_page.save_fail_authority_exists"));
        }
        viewPageEntity1.setName(viewPageEntity.getName());
        viewPageEntity1.setUrl(viewPageEntity.getUrl());
        viewPageEntity1.setAuthority(authority);
        viewPageEntity1.setViewPageCategoryId(viewPageEntity.getViewPageCategoryId());
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

    @Override
    public Pagination<ViewPageEntity> pageAllByViewPageCategoryId(Integer page, Integer rows, Long viewPageCategoryId, Long roleId) {
        Pagination<ViewPageEntity> pagination = new Pagination<>();
        //        if(sorterField != null && !"".equals(sorterField) && sorterOrder != null && !"".equals(sorterOrder)) {
//            sorterField = StringUtil.camelToUnderline(sorterField);
//            String orderBy = sorterField + " " + sorterOrder;
//            PageHelper.startPage(page, rows, orderBy);
//        } else {
        PageHelper.startPage(page, rows);
//        }
        Page<ViewPageEntity> viewPageEntityPage = viewPageMapper.pageAllByViewPageCategoryId(viewPageCategoryId);
        List<ViewPageEntity> viewPageEntityList = viewPageEntityPage.getResult();
        List<ViewPageEntity> viewPageEntityListResult = new ArrayList<>();
        Integer isOrNotIs = Integer.valueOf((String) dictionaryService.get("IS_OR_NOT", "IS"));
        Integer isOrNotNot = Integer.valueOf((String) dictionaryService.get("IS_OR_NOT", "NOT"));
        if (!viewPageEntityList.isEmpty()) {
            for (ViewPageEntity viewPageEntity : viewPageEntityList) {
                if (roleAuthorityMapper.getOneByRoleIdAndAuthority(roleId, viewPageEntity.getAuthority()) != null) {
                    viewPageEntity.setIsGranted(isOrNotIs);
                } else {
                    viewPageEntity.setIsGranted(isOrNotNot);
                }
                viewPageEntityListResult.add(viewPageEntity);
            }
        }
        pagination.setRows(viewPageEntityListResult);
        pagination.setTotal(viewPageEntityPage.getTotal());
        return pagination;
    }
}
