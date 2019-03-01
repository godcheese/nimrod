package com.gioov.nimrod.quartz.entity;

import java.util.Date;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-02-12
 */
public class JobRuntimeLogEntity {

    private Long id;
    private String jobClassName;
    private String jobGroup;
    private String description;
    private Date fireTime;
    private Date nextFireTime;
    private Long jobRunTime;
    private String log;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getFireTime() {
        return fireTime;
    }

    public void setFireTime(Date fireTime) {
        this.fireTime = fireTime;
    }

    public Date getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(Date nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    public Long getJobRunTime() {
        return jobRunTime;
    }

    public void setJobRunTime(Long jobRunTime) {
        this.jobRunTime = jobRunTime;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
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

    @Override
    public String toString() {
        return "JobLogEntity{" +
                "jobClassName='" + jobClassName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", description='" + description + '\'' +
                ", fireTime=" + fireTime +
                ", nextFireTime=" + nextFireTime +
                ", jobRunTime=" + jobRunTime +
                ", log='" + log + '\'' +
                ", jobException='" + jobException + '\'' +
                ", gmtCreated=" + gmtCreated +
                '}';
    }
}
