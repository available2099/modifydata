package com.demo.ai.entity;

import java.io.Serializable;

/**
 * (Inbound)实体类
 *
 * @author makejava
 * @since 2020-12-24 16:53:12
 */
public class Inbound implements Serializable {
    private static final long serialVersionUID = -52776607048649041L;

    private Integer id;
    /**
     * 服务器域名地址
     */
    private String server;

    private Integer port;

    private String listen;

    private String protocol;

    private String settings;

    private String streamSettings;

    private String tag;

    private String sniffing;

    private String remark;

    private Long up;

    private Long down;

    private Integer enable;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getListen() {
        return listen;
    }

    public void setListen(String listen) {
        this.listen = listen;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    public String getStreamSettings() {
        return streamSettings;
    }

    public void setStreamSettings(String streamSettings) {
        this.streamSettings = streamSettings;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSniffing() {
        return sniffing;
    }

    public void setSniffing(String sniffing) {
        this.sniffing = sniffing;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

}