package com.demo.ai.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * jd_plantBean(JdHelp)实体类
 *
 * @author makejava
 * @since 2020-12-24 17:23:45
 */
public class JdHelp implements Serializable {
    private static final long serialVersionUID = 451571811345864579L;

    private BigInteger id;
    /**
     * 用户MD5
     */
    private String userMd5;

    private String uniqueId;
    /**
     * 名字
     */
    private String name;
    /**
     * 用户ck
     */
    private String userCk;
    /**
     * 任务类型
     */
    private String taskType;
    /**
     * 用户ip
     */
    private String ip;
    /**
     * 总请求次数
     */
    private Integer count;
    /**
     * 今天请求次数
     */
    private Integer todaycount;
    /**
     * 今天剩余有效次数
     */
    private Integer todayEffectcount;
    /**
     * 用户是否可用
     */
    private String userStatus;
    /**
     * 用户今天是否可用
     */
    private String userTodaystatus;
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

    public String getUserMd5() {
        return userMd5;
    }

    public void setUserMd5(String userMd5) {
        this.userMd5 = userMd5;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserCk() {
        return userCk;
    }

    public void setUserCk(String userCk) {
        this.userCk = userCk;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTodaycount() {
        return todaycount;
    }

    public void setTodaycount(Integer todaycount) {
        this.todaycount = todaycount;
    }

    public Integer getTodayEffectcount() {
        return todayEffectcount;
    }

    public void setTodayEffectcount(Integer todayEffectcount) {
        this.todayEffectcount = todayEffectcount;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserTodaystatus() {
        return userTodaystatus;
    }

    public void setUserTodaystatus(String userTodaystatus) {
        this.userTodaystatus = userTodaystatus;
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