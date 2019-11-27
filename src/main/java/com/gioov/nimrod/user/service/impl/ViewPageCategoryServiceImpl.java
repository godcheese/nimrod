package com.gioov.nimrod.user.service.impl;

import com.gioov.nimrod.common.others.FailureEntity;
import com.gioov.nimrod.common.easyui.ComboTree;
import com.gioov.nimrod.common.easyui.EasyUI;
import com.gioov.nimrod.user.entity.ViewPageCategoryEntity;
import com.gioov.nimrod.user.entity.ViewPageEntity;
import com.gioov.nimrod.user.mapper.RoleAuthorityMapper;
import com.gioov.nimrod.user.mapper.ViewPageCategoryMapper;
import com.gioov.nimrod.user.mapper.ViewPageMapper;
import com.gioov.nimrod.user.service.ViewPageCategoryService;
import com.gioov.tile.web.exception.BaseResponseException;
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
public class ViewPageCategoryServiceImpl implements ViewPageCategoryService {

    @Autowired
    private ViewPageCategoryMapper viewPageCategoryMapper;
    @Autowired
    private ViewPageMapper viewPageMapper;
    @Autowired
    private FailureEntity failureEntity;
    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Override
    public ViewPageCategoryEntity addOne(ViewPageCategoryEntity viewPageCategoryEntity) {
        Date date = new Date();
        viewPageCategoryEntity.setGmtModified(date);
        viewPageCategoryEntity.setGmtCreated(date);
        viewPageCategoryMapper.insertOne(viewPageCategoryEntity);
        return viewPageCategoryEntity;
    }

    @Override
    public ViewPageCategoryEntity saveOne(ViewPageCategoryEntity viewPageCategoryEntity) {
        ViewPageCategoryEntity viewPageCategoryEntity1 = viewPageCategoryMapper.getOne(viewPageCategoryEntity.getId());
        viewPageCategoryEntity1.setName(viewPageCategoryEntity.getName());
        viewPageCategoryEntity1.setParentId(viewPageCategoryEntity.getParentId());
        viewPageCategoryEntity1.setSort(viewPageCategoryEntity.getSort());
        viewPageCategoryEntity1.setRemark(viewPageCategoryEntity.getRemark());
        viewPageCategoryEntity1.setGmtModified(new Date());
        viewPageCategoryMapper.updateOne(viewPageCategoryEntity1);
        return viewPageCategoryEntity1;
    }

    @Override
    public int deleteAll(List<Long> idList) throws BaseResponseException {
        int result = 0;
        for (Long id : idList) {
            ViewPageCategoryEntity viewPageCategoryEntity = viewPageCategoryMapper.getOneByParentId(id);
            if (viewPageCategoryEntity != null) {
                throw new BaseResponseException(failureEntity.i18n("view_page_category.delete_fail_has_children_category"));
            }
            ViewPageEntity viewPageEntity = viewPageMapper.getOneByViewPageCategoryId(id);
            if (viewPageEntity != null) {
                throw new BaseResponseException(failureEntity.i18n("view_page_category.delete_fail_has_view_page"));
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

    @Override
    public List<ViewPageCategoryEntity> listAllParent() {
        List<ViewPageCategoryEntity> viewPageCategoryEntityList = viewPageCategoryMapper.listAllByParentIdIsNull();
        List<ViewPageCategoryEntity> viewPageCategoryEntityListResult = new ArrayList<>();
        for(ViewPageCategoryEntity viewPageCategoryEntity : viewPageCategoryEntityList) {
            if(viewPageCategoryMapper.getOneByParentId(viewPageCategoryEntity.getId()) != null) {
                viewPageCategoryEntity.setState(EasyUI.State.CLOSED);
            }
            viewPageCategoryEntityListResult.add(viewPageCategoryEntity);
        }
        return viewPageCategoryEntityListResult;
    }

    @Override
    public List<ViewPageCategoryEntity> listAllByParentId(Long parentId) {
        List<ViewPageCategoryEntity> viewPageCategoryEntityList = viewPageCategoryMapper.listAllByParentId(parentId);
        List<ViewPageCategoryEntity> viewPageCategoryEntityListResult = new ArrayList<>();
        for(ViewPageCategoryEntity viewPageCategoryEntity : viewPageCategoryEntityList) {
            if(viewPageCategoryMapper.getOneByParentId(viewPageCategoryEntity.getId()) != null) {
                viewPageCategoryEntity.setState(EasyUI.State.CLOSED);
            }
            viewPageCategoryEntityListResult.add(viewPageCategoryEntity);
        }
        return viewPageCategoryEntityListResult;
    }

    @Override
    public List<ComboTree> listAllViewPageCategoryComboTree() {
        List<ComboTree> comboTreeList = new ArrayList<>(0);
        List<ViewPageCategoryEntity> viewPageCategoryEntityList = viewPageCategoryMapper.listAll();
        for(ViewPageCategoryEntity viewPageCategoryEntity : viewPageCategoryEntityList) {
            ComboTree comboTree = new ComboTree();
            comboTree.setId(viewPageCategoryEntity.getId());
            comboTree.setText(viewPageCategoryEntity.getName());
            comboTree.setParentId(viewPageCategoryEntity.getParentId());
            comboTreeList.add(comboTree);
        }
        return comboTreeList;
    }
    @Override
    public List<ComboTree> getViewPageCategoryChildrenComboTree(long parentId, List<ComboTree> viewPageCategoryComboTreeList) {
        List<ComboTree> children = new ArrayList<>(0);
        for(ComboTree comboTree : viewPageCategoryComboTreeList) {
            if(comboTree.getParentId() != null && comboTree.getParentId().equals(parentId)) {
                children.add(comboTree);
            }
        }
        for(ComboTree child : children) {
            List<ComboTree> childChildren = getViewPageCategoryChildrenComboTree(child.getId(), viewPageCategoryComboTreeList);
            child.setChildren(childChildren);
        }
        if(children.size() == 0) {
            return null;
        }
        return children;
    }

}
