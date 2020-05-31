package com.gioov.nimrod.user.entity;

import com.gioov.nimrod.common.others.BaseEntityAdapter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class UserEntity extends BaseEntityAdapter<UserEntity> implements Serializable, Cloneable {
    private static final long serialVersionUID = -4809374154449809L;
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 电子邮箱是否已验证
     */
    private Integer emailIsVerified;

    /**
     * 所在部门 id
     */
    private Long departmentId;

    /**
     * 所在部门
     */
    private List<DepartmentEntity> department;

    /**
     * 是否启用（0=否，1=是，默认=0）
     */
    private Integer enabled;

    /**
     * 备注
     */
    private String remark;

    private Date gmtPasswordLastModified;

    private Date gmtLastLogin;

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

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public List<DepartmentEntity> getDepartment() {
        return department;
    }

    public void setDepartment(List<DepartmentEntity> department) {
        this.department = department;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getGmtPasswordLastModified() {
        return gmtPasswordLastModified;
    }

    public void setGmtPasswordLastModified(Date gmtPasswordLastModified) {
        this.gmtPasswordLastModified = gmtPasswordLastModified;
    }

    public Date getGmtLastLogin() {
        return gmtLastLogin;
    }

    public void setGmtLastLogin(Date gmtLastLogin) {
        this.gmtLastLogin = gmtLastLogin;
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
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", emailIsVerified=" + emailIsVerified +
                ", departmentId=" + departmentId +
                ", department=" + department +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                ", gmtPasswordLastModified=" + gmtPasswordLastModified +
                ", gmtLastLogin=" + gmtLastLogin +
                ", gmtDeleted=" + gmtDeleted +
                ", gmtModified=" + gmtModified +
                ", gmtCreated=" + gmtCreated +
                '}';
    }
}
