package com.gioov.nimrod.user.entity;

import java.io.Serializable;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class ViewPageComponentApiEntity implements Serializable {


    private static final long serialVersionUID = 9177243332779548374L;
    private Long id;

    /**
     * 视图页面组件 id
     */
    private Long viewPageComponentId;

    /**
     * API id
     */
    private Long apiId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getViewPageComponentId() {
        return viewPageComponentId;
    }

    public void setViewPageComponentId(Long viewPageComponentId) {
        this.viewPageComponentId = viewPageComponentId;
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

}
