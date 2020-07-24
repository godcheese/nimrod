package com.godcheese.nimrod.user.entity;

import java.io.Serializable;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class ViewPageApiEntity implements Serializable {

    private static final long serialVersionUID = 5514010345109428863L;

    private Long id;

    /**
     * 视图页面 id
     */
    private Long viewPageId;

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

    public Long getViewPageId() {
        return viewPageId;
    }

    public void setViewPageId(Long viewPageId) {
        this.viewPageId = viewPageId;
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

}
