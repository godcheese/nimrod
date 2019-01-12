package com.gioov.nimrod.system.entity;

import java.io.Serializable;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class ViewPageApiEntity implements Serializable {

    private static final long serialVersionUID = 8176607833964698661L;

    private Long id;

    /**
     * 视图页面 id
     */
    private Long pageId;

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

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

}
