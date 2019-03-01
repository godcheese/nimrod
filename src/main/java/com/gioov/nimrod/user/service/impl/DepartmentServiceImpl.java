package com.gioov.nimrod.user.service.impl;

import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.user.entity.DepartmentEntity;
import com.gioov.nimrod.user.entity.UserEntity;
import com.gioov.nimrod.user.mapper.DepartmentMapper;
import com.gioov.nimrod.user.mapper.UserMapper;
import com.gioov.nimrod.user.service.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Pagination.Result<DepartmentEntity> pageAllByParentIdIsNull(Integer page, Integer rows) {
        Pagination.Result<DepartmentEntity> paginationResult = new Pagination().new Result<>();
        List<DepartmentEntity> departmentEntityList = departmentMapper.pageAllByParentIdIsNull(new com.gioov.common.mybatis.Pageable(page, rows));
        if (departmentEntityList != null) {
            paginationResult.setRows(departmentEntityList);
        }
        paginationResult.setTotal(departmentMapper.countAllByParentIdIsNull());
        return paginationResult;
    }

    @Override
    public List<DepartmentEntity> listAllByParentId(Long parentId) {
        return departmentMapper.listAllByParentId(parentId);
    }

    @Override
    public Pagination.Result<DepartmentEntity> pageAll(Integer page, Integer rows) {
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
        DepartmentEntity departmentEntity1 = new DepartmentEntity();
        Date date = new Date();
        departmentEntity1.setName(departmentEntity.getName());
        departmentEntity1.setParentId(departmentEntity.getParentId());
        departmentEntity1.setRemark(departmentEntity.getRemark());
        departmentEntity1.setGmtModified(date);
        departmentEntity1.setGmtCreated(date);
        departmentMapper.insertOne(departmentEntity1);
        return departmentEntity1;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DepartmentEntity updateOne(DepartmentEntity departmentEntity) {
        DepartmentEntity departmentEntity1 = new DepartmentEntity();
        departmentEntity1.setId(departmentEntity.getId());
        departmentEntity1.setName(departmentEntity.getName());
        departmentEntity1.setParentId(departmentEntity.getParentId());
        departmentEntity1.setRemark(departmentEntity.getRemark());
        departmentEntity1.setGmtModified(new Date());
        departmentMapper.updateOne(departmentEntity1);
        return departmentEntity1;
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

}
