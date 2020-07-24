package com.godcheese.nimrod.system.entity;

import com.godcheese.nimrod.common.others.BaseEntityAdapter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class OperationLogEntity extends BaseEntityAdapter<OperationLogEntity> implements Serializable {

    private static final long serialVersionUID = 723090697169069573L;

    private Long id;

    /**
     * 访问用户 id
     */
    private Long userId;

    /**
     * 用户 IP
     */
    private String ipAddress;

    /**
     * 操作类型
     */
    private Integer operationType;

    /**
     * 操作说明
     */
    private String operation;

    /**
     * 请求耗时（毫秒）consumingTime
     */
    private Long consumingTime;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求参数
     */
    private String requestParameter;

    /**
     * 请求语言
     */
    private String acceptLanguage;

    /**
     * 请求来源
     */
    private String referer;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * Handler
     */
    private String handler;

    /**
     * 异常堆栈
     */
    private String stackTrace;

    /**
     * Session ID
     */
    private String sessionId;

    /**
     * Cookie
     */
    private String cookie;

    /**
     * 响应状态码
     */
    private String status;

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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Long getConsumingTime() {
        return consumingTime;
    }

    public void setConsumingTime(Long consumingTime) {
        this.consumingTime = consumingTime;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestParameter() {
        return requestParameter;
    }

    public void setRequestParameter(String requestParameter) {
        this.requestParameter = requestParameter;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    @Override
    public String toString() {
        return "OperationLogEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", ipAddress='" + ipAddress + '\'' +
                ", operationType=" + operationType +
                ", operation='" + operation + '\'' +
                ", consumingTime=" + consumingTime +
                ", requestUrl='" + requestUrl + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", requestParameter='" + requestParameter + '\'' +
                ", acceptLanguage='" + acceptLanguage + '\'' +
                ", referer='" + referer + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", handler='" + handler + '\'' +
                ", stackTrace='" + stackTrace + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", cookie='" + cookie + '\'' +
                ", status='" + status + '\'' +
                ", gmtCreated=" + gmtCreated +
                '}';
    }
}
