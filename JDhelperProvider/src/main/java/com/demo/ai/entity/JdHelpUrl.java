package com.demo.ai.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * jd_plantBean(JdHelpUrl)实体类
 *
 * @author makejava
 * @since 2020-12-24 19:43:06
 */
public class JdHelpUrl implements Serializable {
    private static final long serialVersionUID = -84301070422734637L;

    private BigInteger id;
    /**
     * 任务类型
     */
    private String taskType;
    /**
     * 任务url
     */
    private String url;
    /**
     * 是否可用
     */
    private String status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;


    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}