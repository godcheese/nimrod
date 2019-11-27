package com.gioov.nimrod.common.easyui;

import java.io.Serializable;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-05-04
 */
public class ComboTree implements Serializable {
    private static final long serialVersionUID = 962965761257333089L;
    private Long id;
    private String text;
    private Long parentId;
    private List<ComboTree> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<ComboTree> getChildren() {
        return children;
    }

    public void setChildren(List<ComboTree> children) {
        this.children = children;
    }
}
