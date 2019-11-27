package com.gioov.nimrod.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class UserVerifyCodeEntity implements Serializable {
    private static final long serialVersionUID = -7053740452414479328L;
    /**
     * id
     */
    private Long id;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 用户绑定的电子邮箱或手机号码
     */
    private String verifyFrom;

    /**
     * 电子邮箱或手机号码验证码
     */
    private String verifyCode;

    /**
     * 过期时间
     */
    private Date gmtExpires;

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

    public String getVerifyFrom() {
        return verifyFrom;
    }

    public void setVerifyFrom(String verifyFrom) {
        this.verifyFrom = verifyFrom;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Date getGmtExpires() {
        return gmtExpires;
    }

    public void setGmtExpires(Date gmtExpires) {
        this.gmtExpires = gmtExpires;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

}
