package com.demo.ai.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 失败任务(FailedNodeJobs)实体类
 *
 * @author makejava
 * @since 2020-12-24 16:53:08
 */
public class FailedNodeJobs implements Serializable {
    private static final long serialVersionUID = -62336287956719950L;

    private Long id;
    /**
     * 创建任务ip
     */
    private String createIp;
    /**
     * 需要执行任务服务器域名地址
     */
    private String destnationIp;
    /**
     * 请求服务器域名地址
     */
    private String server;

    private String json;
    /**
     * job执行次数
     */
    private Integer count;

    private Date failedAt;
    /**
     * 状态：0-删除、1-待执行
     */
    private Object status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public String getDestnationIp() {
        return destnationIp;
    }

    public void setDestnationIp(String destnationIp) {
        this.destnationIp = destnationIp;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getFailedAt() {
        return failedAt;
    }

    public void setFailedAt(Date failedAt) {
        this.failedAt = failedAt;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

}