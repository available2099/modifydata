package com.demo.ai.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (UserSubscribe)实体类
 *
 * @author makejava
 * @since 2020-12-24 16:53:32
 */
public class UserSubscribe implements Serializable {
    private static final long serialVersionUID = -48775159272028800L;

    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 订阅地址唯一识别码
     */
    private String code;
    /**
     * 用户使用的V2ray端口
     */
    private Integer userPort;

    private String fqText;
    /**
     * 可用流量，单位字节，默认1TiB
     */
    private Long transferEnable;
    /**
     * 已上传流量，单位字节
     */
    private Long up;
    /**
     * 已下载流量，单位字节
     */
    private Long down;
    /**
     * 总流量，单位字节
     */
    private Long alllink;
    /**
     * 地址请求次数
     */
    private Integer times;
    /**
     * 状态：0-禁用、1-启用
     */
    private Object status;
    /**
     * 封禁理由
     */
    private String banDesc;
    /**
     * 微信
     */
    private String wechat;
    /**
     * QQ
     */
    private String qq;
    /**
     * 用途：1-手机、2-电脑、3-路由器、4-其他
     */
    private String usage;
    /**
     * 付费方式：0-免费、1-季付、2-月付、3-半年付、4-年付
     */
    private Object payWay;
    /**
     * 余额，单位分
     */
    private Integer balance;
    /**
     * 开通日期
     */
    private Object enableTime;
    /**
     * 过期时间
     */
    private Object expireTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 等级：可定义名称
     */
    private Object level;
    /**
     * 是否管理员：0-否、1-是
     */
    private Object isAdmin;
    /**
     * 流量自动重置日，0表示不重置
     */
    private Object trafficResetDay;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 最后更新时间
     */
    private Date updatedAt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getUserPort() {
        return userPort;
    }

    public void setUserPort(Integer userPort) {
        this.userPort = userPort;
    }

    public String getFqText() {
        return fqText;
    }

    public void setFqText(String fqText) {
        this.fqText = fqText;
    }

    public Long getTransferEnable() {
        return transferEnable;
    }

    public void setTransferEnable(Long transferEnable) {
        this.transferEnable = transferEnable;
    }

    public Long getUp() {
        return up;
    }

    public void setUp(Long up) {
        this.up = up;
    }

    public Long getDown() {
        return down;
    }

    public void setDown(Long down) {
        this.down = down;
    }

    public Long getAlllink() {
        return alllink;
    }

    public void setAlllink(Long alllink) {
        this.alllink = alllink;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public String getBanDesc() {
        return banDesc;
    }

    public void setBanDesc(String banDesc) {
        this.banDesc = banDesc;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public Object getPayWay() {
        return payWay;
    }

    public void setPayWay(Object payWay) {
        this.payWay = payWay;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Object getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(Object enableTime) {
        this.enableTime = enableTime;
    }

    public Object getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Object expireTime) {
        this.expireTime = expireTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Object getLevel() {
        return level;
    }

    public void setLevel(Object level) {
        this.level = level;
    }

    public Object getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Object isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Object getTrafficResetDay() {
        return trafficResetDay;
    }

    public void setTrafficResetDay(Object trafficResetDay) {
        this.trafficResetDay = trafficResetDay;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}