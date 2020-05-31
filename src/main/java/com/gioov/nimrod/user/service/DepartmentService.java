package com.gioov.nimrod.user.service;

import com.gioov.nimrod.common.easyui.ComboTree;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.easyui.TreeGrid;
import com.gioov.nimrod.user.entity.DepartmentEntity;
import com.gioov.tile.web.exception.BaseResponseException;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public interface DepartmentService {
    /**
     * 新增部门
     *
     * @param departmentEntity DepartmentEntity
     * @return DepartmentEntity
     */
    DepartmentEntity addOne(DepartmentEntity departmentEntity);

    /**
     * 保存部门
     *
     * @param departmentEntity DepartmentEntity
     * @return DepartmentEntity
     */
    DepartmentEntity saveOne(DepartmentEntity departmentEntity);

    /**
     * 指定部门 id list，批量删除部门
     *
     * @param idList 角色 id list
     * @return int 已删除角色个数
     * @throws BaseResponseException BaseResponseException
     */
    int deleteAll(List<Long> idList) throws BaseResponseException;

    /**
     * 指定部门 id，获取部门
     *
     * @param id 部门 id
     * @return DepartmentEntity
     */
    DepartmentEntity getOne(Long id);

    /**
     * 指定父级 API 分类 id，获取所有 API 分类
     *
     * @return List<ApiCategoryEntity>
     */
    List<DepartmentEntity> listAllParent();

    /**
     * 指定父级部门 id，获取所有部门
     *
     * @param parentId 父级部门 id
     * @return List<DepartmentEntity>
     */
    List<DepartmentEntity> listAllByParentId(Long parentId);

//    /**
//     * 指定用户角色 list，获取所有角色
//     *
//     * @param userRoleEntityList 用户角色 list
//     * @return List<RoleEntity>
//     */
//    List<DepartmentEntity> listAllByUserRoleList(List<UserRoleEntity> userRoleEntityList);

    /**
     * 指定 API 分类 id，分页获取所有 API
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return Pagination<DepartmentEntity>
     */
    Pagination<DepartmentEntity> pageAll(Integer page, Integer rows);

    /**
     * 获取所有部门
     *
     * @return List<DepartmentEntity>
     */
    List<DepartmentEntity> listAll();

//    /**
//     * 指定用户 id，获取用户角色
//     *
//     * @param userId 用户 id
//     * @return List<RoleEntity>
//     */
//    List<DepartmentEntity> listAllByUserId(Long userId);

    /**
     * 指定部门 id，获取所有部门
     *
     * @param id 部门 id
     * @return List<DepartmentEntity>
     */
    List<DepartmentEntity> listAllByDepartmentId(Long id);

    /**
     * 获取所有部门，以 EasyUI ComboTree 形式展示
     *
     * @return List<ComboTree>
     */
    List<ComboTree> listAllDepartmentComboTree();

    /**
     * 指定父级部门 id，DepartmentComboTree list，以 EasyUI ComboTree 形式展示
     *
     * @param parentId                父级部门 id
     * @param departmentComboTreeList DepartmentComboTree list
     * @return List<ComboTree>
     */
    List<ComboTree> getDepartmentChildrenComboTree(long parentId, List<ComboTree> departmentComboTreeList);

    /**
     * 获取所有部门，以 EasyUI TreeGrid 形式展示
     *
     * @return List<TreeGrid>
     */
    List<TreeGrid> listAllDepartmentTreeGrid();

    /**
     * 指定父级部门 id，DepartmentTreeGrid list，获取所有子级部门，以 EasyUI TreeGrid 形式展示
     *
     * @param parentId               父级部门 id
     * @param departmentTreeGridList DepartmentTreeGrid list
     * @return List<TreeGrid>
     */
    List<TreeGrid> getDepartmentChildrenTreeGrid(long parentId, List<TreeGrid> departmentTreeGridList);

}
