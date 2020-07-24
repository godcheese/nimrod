package com.godcheese.nimrod.user.entity;

import java.io.Serializable;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class RoleViewMenuEntity implements Serializable {

    private static final long serialVersionUID = 4539087168395191315L;

    /**
     * id
     */
    private Long id;

    /**
     * 角色 id
     */
    private Long roleId;

    /**
     * 视图菜单 id
     */
    private String viewMenuId;


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

    public String getViewMenuId() {
        return viewMenuId;
    }

    public void setViewMenuId(String viewMenuId) {
        this.viewMenuId = viewMenuId;
    }
}
