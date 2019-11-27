package com.gioov.nimrod.user.entity;

import java.io.Serializable;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class RoleViewMenuCategoryEntity implements Serializable {


    private static final long serialVersionUID = -6845129368544578850L;
    /**
     * id
     */
    private Long id;

    /**
     * 角色 id
     */
    private Long roleId;

    /**
     * 视图菜单分类 id
     */
    private String viewMenuCategoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getViewMenuCategoryId() {
        return viewMenuCategoryId;
    }

    public void setViewMenuCategoryId(String viewMenuCategoryId) {
        this.viewMenuCategoryId = viewMenuCategoryId;
    }
}
