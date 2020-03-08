package com.gioov.nimrod.user.service.impl;

import com.gioov.nimrod.common.easyui.ComboTree;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.easyui.TreeGrid;
import com.gioov.nimrod.common.others.FailureEntity;
import com.gioov.nimrod.user.entity.DepartmentEntity;
import com.gioov.nimrod.user.entity.UserEntity;
import com.gioov.nimrod.user.mapper.DepartmentMapper;
import com.gioov.nimrod.user.mapper.UserMapper;
import com.gioov.nimrod.user.service.DepartmentService;
import com.gioov.tile.web.exception.BaseResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
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
    @Autowired
    private FailureEntity failureEntity;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DepartmentEntity addOne(DepartmentEntity departmentEntity) {
        Date date = new Date();
        departmentEntity.setGmtModified(date);
        departmentEntity.setGmtCreated(date);
        departmentMapper.insertOne(departmentEntity);
        return departmentEntity;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DepartmentEntity saveOne(DepartmentEntity departmentEntity) {
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
                throw new BaseResponseException(failureEntity.i18n("department.delete_fail_has_user"));
            }
            DepartmentEntity departmentEntity = departmentMapper.getOneByParentId(id);
            if (departmentEntity != null) {
                throw new BaseResponseException(failureEntity.i18n("department.delete_fail_has_children_department"));
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

    /**
     * 根据子级部门 id 获取所有父级部门
     * @param id
     * @return
     */
    @Override
    public List<DepartmentEntity> listAllByDepartmentId(Long id) {
        List<DepartmentEntity> departmentEntityResultList = new ArrayList<>(0);
        List<DepartmentEntity> departmentEntityList = listAll();
        DepartmentEntity departmentEntity = getOne(id);
        departmentEntityResultList.add(departmentEntity);
        forEachDepartmentParent(departmentEntity, departmentEntityList, departmentEntityResultList);
        Collections.reverse(departmentEntityResultList);
        return departmentEntityResultList;
    }
    public void forEachDepartmentParent(DepartmentEntity departmentEntity, List<DepartmentEntity> departmentEntityList, List<DepartmentEntity> departmentEntityResultList) {
        for(DepartmentEntity entity : departmentEntityList) {
            if(departmentEntity.getParentId() != null) {
                if(departmentEntity.getParentId().equals(entity.getId())){
                    departmentEntityResultList.add(entity);
                    forEachDepartmentParent(entity, departmentEntityList, departmentEntityResultList);
                }
            }
        }
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
    public List<ComboTree> getDepartmentChildrenComboTree(long parentId, List<ComboTree> departmentComboTreeList) {

        List<ComboTree> children = new ArrayList<>(0);

        for(ComboTree comboTree : departmentComboTreeList) {
            if(comboTree.getParentId() != null && comboTree.getParentId().equals(parentId)) {
                children.add(comboTree);
            }
        }

        for(ComboTree child : children) {

            List<ComboTree> childChildren = getDepartmentChildrenComboTree(child.getId(), departmentComboTreeList);
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
    public List<TreeGrid> getDepartmentChildrenTreeGrid(long parentId, List<TreeGrid> departmentTreeGridList) {

        List<TreeGrid> children = new ArrayList<>(0);
        for(TreeGrid treeGrid : departmentTreeGridList) {
            if(treeGrid.getParentId() != null && treeGrid.getParentId().equals(parentId)) {
                children.add(treeGrid);
            }
        }

        for(TreeGrid child : children) {

            List<TreeGrid> childChildren = getDepartmentChildrenTreeGrid(child.getId(), departmentTreeGridList);
            if(childChildren == null) {
                childChildren = new ArrayList<>(0);
            }
            child.setChildren(childChildren);
        }
        return children;
    }

}
