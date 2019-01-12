package com.gioov.nimrod.common.easyui;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class Accordion {

    /**
     * node id
     */
    private long id;

    /**
     * node title to show
     */
    private String title;

    /**
     * The css class to display icon.
     */
    private String iconCls;

    private String content;

    /**
     * Set to true to expand the panel.
     */
    private boolean selected = false;

    /**
     * Defines if to show collapsible button. False will prevent from clicking to expand/collapse this panel.
     */
    private boolean collapsible = true;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isCollapsible() {
        return collapsible;
    }

    public void setCollapsible(boolean collapsible) {
        this.collapsible = collapsible;
    }

}
