package com.gioov.nimrod.common.easyui;

import com.gioov.nimrod.common.others.CommonEntityAdapter;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-09-26
 */
public abstract class TreeGridAdapter<T> extends CommonEntityAdapter<T> {
    public List<T> children;
    public String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }
}
