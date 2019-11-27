package com.gioov.nimrod.quartz.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-02-12
 */
public class JobRuntimeLogEntity implements Serializable {
    private static final long serialVersionUID = 7788756745999894643L;
    private Long id;
    private String jobClassName;
    private String jobGroup;
    private Long consumingTime;
    private String jobException;
    private Date gmtCreated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public Long getConsumingTime() {
        return consumingTime;
    }

    public void setConsumingTime(Long consumingTime) {
        this.consumingTime = consumingTime;
    }

    public String getJobException() {
        return jobException;
    }

    public void setJobException(String jobException) {
        this.jobException = jobException;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

}
