package com.gioov.nimrod.common.easyui;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class Tree implements Serializable {
    private static final long serialVersionUID = -7372612079113611533L;
    /**
     * node id, which is important to load remote data
     */
    private long id;

    /**
     * node text to show
     */
    private String text;

    /**
     * The css class to display icon.
     */
    private String iconCls;

    /**
     * node state, 'open' or 'closed', default is 'open'. When set to 'closed', the node have children nodes and will load them from remote site
     */
    private String state = "open";

    /**
     * Indicate whether the node is checked selected.
     */
    private boolean checked;

    /**
     * custom attributes can be added to a node.
     */
    private List<Map<String, String>> attributes;

    /**
     * an array nodes defines some children nodes.
     */
    private List<Tree> children = Collections.emptyList();
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<Map<String, String>> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Map<String, String>> attributes) {
        this.attributes = attributes;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }
}
