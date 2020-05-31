package com.gioov.nimrod.user.service.impl;

import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.others.FailureEntity;
import com.gioov.nimrod.system.service.DictionaryService;
import com.gioov.nimrod.user.entity.ViewPageComponentEntity;
import com.gioov.nimrod.user.mapper.RoleAuthorityMapper;
import com.gioov.nimrod.user.mapper.ViewPageComponentMapper;
import com.gioov.nimrod.user.service.ViewPageComponentService;
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
public class ViewPageComponentServiceImpl implements ViewPageComponentService {

    @Autowired
    private ViewPageComponentMapper viewPageComponentMapper;
    @Autowired
    private FailureEntity failureEntity;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Override
    public ViewPageComponentEntity addOne(ViewPageComponentEntity viewPageComponentEntity) throws BaseResponseException {
        Date date = new Date();
        String authority = viewPageComponentEntity.getAuthority().toUpperCase();
        if (viewPageComponentMapper.getOneByAuthority(authority) != null) {
            throw new BaseResponseException(failureEntity.i18n("view_page_component.add_fail_authority_exists"));
        }
        viewPageComponentEntity.setAuthority(authority);
        viewPageComponentEntity.setGmtModified(date);
        viewPageComponentEntity.setGmtCreated(date);
        viewPageComponentMapper.insertOne(viewPageComponentEntity);
        return viewPageComponentEntity;
    }

    @Override
    public ViewPageComponentEntity saveOne(ViewPageComponentEntity viewPageComponentEntity) throws BaseResponseException {
        ViewPageComponentEntity viewPageComponentEntity1 = viewPageComponentMapper.getOne(viewPageComponentEntity.getId());
        String authority = viewPageComponentEntity.getAuthority().toUpperCase();
        ViewPageComponentEntity viewPageComponentEntity2 = viewPageComponentMapper.getOneByAuthority(authority);
        if (viewPageComponentEntity2 != null && !viewPageComponentEntity2.getId().equals(viewPageComponentEntity.getId())) {
            throw new BaseResponseException(failureEntity.i18n("view_page_component.save_fail_authority_exists"));
        }
        viewPageComponentEntity1.setViewPageComponentType(viewPageComponentEntity.getViewPageComponentType());
        viewPageComponentEntity1.setName(viewPageComponentEntity.getName());
        viewPageComponentEntity1.setAuthority(authority);
        viewPageComponentEntity1.setSort(viewPageComponentEntity.getSort());
        viewPageComponentEntity1.setRemark(viewPageComponentEntity.getRemark());
        viewPageComponentEntity1.setGmtModified(new Date());
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

    @Override
    public Pagination<ViewPageComponentEntity> pageAllByViewPageId(Integer page, Integer rows, Long viewPageId, Long roleId) {
        Pagination<ViewPageComponentEntity> pagination = new Pagination<>();
        PageHelper.startPage(page, rows);
        Page<ViewPageComponentEntity> viewPageComponentEntityPage = viewPageComponentMapper.pageAllByViewPageId(viewPageId);
        List<ViewPageComponentEntity> viewPageComponentEntityList = viewPageComponentEntityPage.getResult();
        List<ViewPageComponentEntity> viewPageComponentEntityListResult = new ArrayList<>();
        Integer isOrNotIs = Integer.valueOf((String) dictionaryService.get("IS_OR_NOT", "IS"));
        Integer isOrNotNot = Integer.valueOf((String) dictionaryService.get("IS_OR_NOT", "NOT"));
        if (!viewPageComponentEntityList.isEmpty()) {
            for (ViewPageComponentEntity viewPageComponentEntity : viewPageComponentEntityList) {

                if (roleAuthorityMapper.getOneByRoleIdAndAuthority(roleId, viewPageComponentEntity.getAuthority()) != null) {
                    viewPageComponentEntity.setIsGranted(isOrNotIs);
                } else {
                    viewPageComponentEntity.setIsGranted(isOrNotNot);
                }
                viewPageComponentEntityListResult.add(viewPageComponentEntity);
            }
        }
        pagination.setRows(viewPageComponentEntityListResult);
        pagination.setTotal(viewPageComponentEntityPage.getTotal());
        return pagination;
    }

}
