package com.demo.ai.entity;

import java.io.Serializable;

/**
 * (VpsDevice)实体类
 *
 * @author makejava
 * @since 2020-12-24 16:53:39
 */
public class VpsDevice implements Serializable {
    private static final long serialVersionUID = 382633784559715971L;

    private Integer id;
    /**
     * 设备名称
     */
    private String name;
    /**
     * 类型：0,1,2,3,4普通到VIP4
     */
    private Object level;
    /**
     * 国家代码
     */
    private String countryCode;
    /**
     * 服务器域名地址
     */
    private String server;
    /**
     * 服务器IPV4地址
     */
    private String ip;
    /**
     * 总流量，单位字节
     */
    private Long alllink;
    /**
     * 状态：0-不可用、1-可用
     */
    private Object status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getLevel() {
        return level;
    }

    public void setLevel(Object level) {
        this.level = level;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getAlllink() {
        return alllink;
    }

    public void setAlllink(Long alllink) {
        this.alllink = alllink;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

}