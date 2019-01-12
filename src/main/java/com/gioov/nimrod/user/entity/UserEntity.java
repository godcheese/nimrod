package com.gioov.nimrod.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class UserEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = 2410416481682685038L;

    /**
     * id
     */
    private Long id;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户名
     */
    private String username;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 电子邮箱是否已验证
     */
    private Integer emailIsVerified;

    /**
     * 部门 id
     */
    private Long departmentId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除时间
     */
    private Date gmtDeleted;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEmailIsVerified() {
        return emailIsVerified;
    }

    public void setEmailIsVerified(Integer emailIsVerified) {
        this.emailIsVerified = emailIsVerified;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getGmtDeleted() {
        return gmtDeleted;
    }

    public void setGmtDeleted(Date gmtDeleted) {
        this.gmtDeleted = gmtDeleted;
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

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", emailIsVerified=" + emailIsVerified +
                ", departmentId=" + departmentId +
                ", remark='" + remark + '\'' +
                ", gmtDeleted=" + gmtDeleted +
                ", gmtModified=" + gmtModified +
                ", gmtCreated=" + gmtCreated +
                '}';
    }

    @Override
    protected UserEntity clone() throws CloneNotSupportedException {
        return (UserEntity) super.clone();
    }
}
