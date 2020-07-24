package com.godcheese.nimrod.user.service.impl;

import com.godcheese.nimrod.user.entity.ViewMenuCategoryEntity;
import com.godcheese.nimrod.user.mapper.RoleViewMenuCategoryMapper;
import com.godcheese.nimrod.user.mapper.ViewMenuCategoryMapper;
import com.godcheese.nimrod.user.service.RoleViewMenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class RoleViewMenuCategoryServiceImpl implements RoleViewMenuCategoryService {
    @Autowired
    private RoleViewMenuCategoryMapper roleViewMenuCategoryMapper;
    @Autowired
    private ViewMenuCategoryMapper viewMenuCategoryMapper;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int grantAllByRoleIdAndViewMenuCategoryIdList(Long roleId, List<Long> viewMenuCategoryIdList) {
        // 最终被添加的 viewMenuCategoryIdList
        List<Long> viewMenuCategoryIdListResult = new ArrayList<>();
        ViewMenuCategoryEntity viewMenuCategoryEntity;
        for (Long viewMenuCategoryId : viewMenuCategoryIdList) {
            viewMenuCategoryEntity = viewMenuCategoryMapper.getOne(viewMenuCategoryId);
            if (viewMenuCategoryEntity == null) {
                viewMenuCategoryIdListResult.add(viewMenuCategoryId);
            }
        }
        // viewMenuCategoryIdList 全部写入数据库
        if (!viewMenuCategoryIdListResult.isEmpty()) {
            roleViewMenuCategoryMapper.insertAllByRoleIdAndViewMenuCategoryIdList(roleId, viewMenuCategoryIdListResult);
        }
        return viewMenuCategoryIdListResult.size();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int revokeAllByRoleIdAndViewMenuCategoryIdList(Long roleId, List<Long> viewMenuCategoryIdList) {
        // 最终被添加的 viewMenuCategoryIdList
        List<Long> viewMenuCategoryIdListResult = new ArrayList<>();
        ViewMenuCategoryEntity viewMenuCategoryEntity;
        for (Long viewMenuCategoryId : viewMenuCategoryIdList) {
            viewMenuCategoryEntity = viewMenuCategoryMapper.getOne(viewMenuCategoryId);
            if (viewMenuCategoryEntity == null) {
                viewMenuCategoryIdListResult.add(viewMenuCategoryId);
            }
        }
        // viewMenuCategoryIdList 全部写入数据库
        if (!viewMenuCategoryIdListResult.isEmpty()) {
            roleViewMenuCategoryMapper.deleteAllByRoleIdAndViewMenuCategoryIdList(roleId, viewMenuCategoryIdListResult);
        }
        return viewMenuCategoryIdListResult.size();
    }
}
