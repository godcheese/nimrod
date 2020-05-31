package com.gioov.nimrod.system.entity;

import com.gioov.nimrod.common.others.BaseEntityAdapter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class FileEntity extends BaseEntityAdapter<FileEntity> implements Serializable {
    private static final long serialVersionUID = -361336044807379339L;
    /**
     * id
     */
    private Long id;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件美化大小
     */
    private String prettySize;

    /**
     * MIME 类型
     */
    private String mimeType;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 唯一标识符
     */
    private String guid;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getPrettySize() {
        return prettySize;
    }

    public void setPrettySize(String prettySize) {
        this.prettySize = prettySize;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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
