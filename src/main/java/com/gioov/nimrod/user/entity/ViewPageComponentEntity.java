package com.gioov.nimrod.user.entity;


import com.gioov.nimrod.common.others.CommonEntityAdapter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class ViewPageComponentEntity extends CommonEntityAdapter<ViewPageComponentEntity> implements Serializable {
    private static final long serialVersionUID = -5628097399101917617L;
    /**
     * id
     */
    private Long id;

    /**
     * 组件分类 id
     */
    private Long viewPageComponentType;

    /**
     * 组件名称
     */
    private String name;

    /**
     * 权限（authority）
     */
    private String authority;

    /**
     * 视图页面 id
     */
    private Long viewPageId;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 更新时间
     */
    private Date gmtModified;

    /**
     * 创建时间
     */
    private Date gmtCreated;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getViewPageComponentType() {
        return viewPageComponentType;
    }

    public void setViewPageComponentType(Long viewPageComponentType) {
        this.viewPageComponentType = viewPageComponentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Long getViewPageId() {
        return viewPageId;
    }

    public void setViewPageId(Long viewPageId) {
        this.viewPageId = viewPageId;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

}
