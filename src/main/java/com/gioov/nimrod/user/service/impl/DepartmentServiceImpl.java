package com.gioov.nimrod.user.service.impl;

import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.easyui.ComboTree;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.easyui.TreeGrid;
import com.gioov.nimrod.user.entity.DepartmentEntity;
import com.gioov.nimrod.user.entity.UserEntity;
import com.gioov.nimrod.user.mapper.DepartmentMapper;
import com.gioov.nimrod.user.mapper.UserMapper;
import com.gioov.nimrod.user.service.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Pagination<DepartmentEntity> pageAllParent(Integer page, Integer rows) {
        Pagination<DepartmentEntity> pagination = new Pagination<>();
        List<DepartmentEntity> departmentEntityList = departmentMapper.pageAllParentIdIsNull(new com.gioov.common.mybatis.Pageable(page, rows));
        if (departmentEntityList != null) {
            pagination.setRows(departmentEntityList);
        }
        pagination.setTotal(departmentMapper.countAllParentIdIsNull());
        return pagination;
    }

    @Override
    public List<DepartmentEntity> listAllParent() {
        return departmentMapper.listAllParentIdIsNull();
    }

    @Override
    public List<DepartmentEntity> listAllByParentId(Long parentId) {
        return departmentMapper.listAllByParentId(parentId);
    }

    @Override
    public Pagination<DepartmentEntity> pageAll(Integer page, Integer rows) {
        return null;
    }

    //    /**
//     * 根据用户关联角色来获取所有角色
//     *
//     * @param userRoleEntityList 用户角色 list
//     * @return List<RoleEntity>
//     */
//    @Override
//    public List<RoleEntity> listAllByUserRoleList(List<UserRoleEntity> userRoleEntityList) {
//        List<RoleEntity> roleEntityList = null;
//        if (userRoleEntityList != null && !userRoleEntityList.isEmpty()) {
//            roleEntityList = new ArrayList<>();
//            for (UserRoleEntity userRoleEntity : userRoleEntityList) {
//                RoleEntity roleEntity = roleMapper.getOne(userRoleEntity.getRoleId());
//                roleEntityList.add(roleEntity);
//            }
//        }
//        return roleEntityList;
//    }

    @Override
    public List<DepartmentEntity> listAll() {
        return departmentMapper.listAll();
    }

//    @Override
//    public List<RoleEntity> listAllByUserId(Long userId) {
//        List<RoleEntity> roleEntityList = new ArrayList<>();
//        List<UserRoleEntity> userRoleEntityList = userRoleMapper.listAllByUserId(userId);
//        if (userRoleEntityList != null && !userRoleEntityList.isEmpty()) {
//            for (UserRoleEntity userRoleEntity : userRoleEntityList) {
//                RoleEntity roleEntity = roleMapper.getOne(userRoleEntity.getRoleId());
//                roleEntityList.add(roleEntity);
//            }
//        }
//        return roleEntityList;
//    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DepartmentEntity insertOne(DepartmentEntity departmentEntity) {
        Date date = new Date();
        departmentEntity.setGmtModified(date);
        departmentEntity.setGmtCreated(date);
        departmentMapper.insertOne(departmentEntity);
        return departmentEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DepartmentEntity updateOne(DepartmentEntity departmentEntity) {
        departmentEntity.setGmtModified(new Date());
        departmentMapper.updateOne(departmentEntity);
        return departmentEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int deleteAll(List<Long> idList) throws BaseResponseException {
        int result = 0;
        for (Long id : idList) {
            UserEntity userEntity = userMapper.getOneByDepartmentId(id);
            if (userEntity != null) {
                throw new BaseResponseException("无法删除该部门，该部门下存在用户");
            }
            DepartmentEntity departmentEntity = departmentMapper.getOneByParentId(id);
            if (departmentEntity != null) {
                throw new BaseResponseException("无法删除该部门，该部门下存在子部门");
            }
            departmentMapper.deleteOne(id);
            result++;
        }
        return result;
    }

    @Override
    public DepartmentEntity getOne(Long id) {
        return departmentMapper.getOne(id);
    }

    @Override
    public List<ComboTree> listAllDepartmentComboTree() {
        List<ComboTree> comboTreeList = new ArrayList<>(0);
        List<DepartmentEntity> departmentEntityList = listAll();
        for(DepartmentEntity departmentEntity : departmentEntityList) {
            ComboTree comboTree = new ComboTree();
            comboTree.setId(departmentEntity.getId());
            comboTree.setText(departmentEntity.getName());
            comboTree.setParentId(departmentEntity.getParentId());
            comboTreeList.add(comboTree);
        }
        return comboTreeList;
    }

    @Override
    public List<ComboTree> getComboTreeChildren(long parentId, List<ComboTree> departmentComboTreeList) {

        List<ComboTree> children = new ArrayList<>(0);

        for(ComboTree comboTree : departmentComboTreeList) {
            if(comboTree.getParentId() != null && comboTree.getParentId().equals(parentId)) {
                children.add(comboTree);
            }
        }

        for(ComboTree child : children) {

            List<ComboTree> childChildren = getComboTreeChildren(child.getId(), departmentComboTreeList);
            if(childChildren == null) {
                childChildren = new ArrayList<>(0);
            }
            child.setChildren(childChildren);
        }

        return children;
    }

    @Override
    public List<TreeGrid> listAllDepartmentTreeGrid() {
        List<TreeGrid> treeGridList = new ArrayList<>(0);
        List<DepartmentEntity> departmentEntityList = listAll();
        for(DepartmentEntity departmentEntity : departmentEntityList) {
            TreeGrid treeGrid = new TreeGrid();
            treeGrid.setId(departmentEntity.getId());
            treeGrid.setName(departmentEntity.getName());
            treeGrid.setParentId(departmentEntity.getParentId());
            treeGridList.add(treeGrid);
        }
        return treeGridList;
    }

    @Override
    public List<TreeGrid> getTreeGridChildren(long parentId, List<TreeGrid> departmentTreeGridList) {

        List<TreeGrid> children = new ArrayList<>(0);

        LOGGER.info("departmentTreeGridList={}", departmentTreeGridList);
        for(TreeGrid treeGrid : departmentTreeGridList) {

            if(treeGrid.getParentId() != null && treeGrid.getParentId().equals(parentId)) {
                children.add(treeGrid);
            }
        }

        for(TreeGrid child : children) {

            List<TreeGrid> childChildren = getTreeGridChildren(child.getId(), departmentTreeGridList);
            if(childChildren == null) {
                childChildren = new ArrayList<>(0);
            }
            child.setChildren(childChildren);
        }
        return children;
    }

}
