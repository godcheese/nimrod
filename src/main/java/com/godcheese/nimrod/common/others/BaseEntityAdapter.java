package com.godcheese.nimrod.common.others;

import com.godcheese.nimrod.user.entity.DepartmentEntity;
import com.godcheese.nimrod.user.entity.RoleEntity;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-09-30
 */
public abstract class BaseEntityAdapter<T> {

    /**
     * 用户名
     */
    private String username;

    /**
     * 角色
     */
    private List<RoleEntity> roles;

    /**
     * 部门
     */
    private List<DepartmentEntity> departments;

    /**
     * 是否已关联
     */
    private Integer isAssociated;

    /**
     * 是否已授权
     */
    private Integer isGranted;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public List<DepartmentEntity> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentEntity> departments) {
        this.departments = departments;
    }

    public Integer getIsAssociated() {
        return isAssociated;
    }

    public void setIsAssociated(Integer isAssociated) {
        this.isAssociated = isAssociated;
    }

    public Integer getIsGranted() {
        return isGranted;
    }

    public void setIsGranted(Integer isGranted) {
        this.isGranted = isGranted;
    }
}
