package com.demo.ai.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (VpsNode)实体类
 *
 * @author makejava
 * @since 2020-12-24 16:53:45
 */
public class VpsNode implements Serializable {
    private static final long serialVersionUID = 463350613536410183L;

    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 服务类型：SS、V2ray
     */
    private String type;
    /**
     * 名称
     */
    private String name;
    /**
     * 所属分组
     */
    private Integer groupId;
    /**
     * 国家代码
     */
    private String countryCode;
    /**
     * 服务器域名地址
     */
    private String server;
    /**
     * 端口tag
     */
    private String tag;
    /**
     * 是否允许用户订阅该节点：0-否、1-是
     */
    private Object isSubscribe;
    /**
     * 排序值，值越大越靠前显示
     */
    private Integer sort;
    /**
     * 状态：0-维护、1-正常
     */
    private Object status;
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
     * 节点简单描述
     */
    private String desc;
    /**
     * V2ray id密码
     */
    private String v2Id;
    /**
     * V2ray额外ID
     */
    private Integer v2AlterId;
    /**
     * V2ray端口
     */
    private Integer v2Port;
    /**
     * V2ray加密方式
     */
    private String v2Method;
    /**
     * V2ray传输协议
     */
    private String v2Net;
    /**
     * V2ray伪装类型
     */
    private String v2Type;
    /**
     * V2ray伪装的域名
     */
    private String v2Host;
    /**
     * V2ray WS/H2路径
     */
    private String v2Path;
    /**
     * V2ray底层传输安全 0 未开启 1 开启
     */
    private Object v2Tls;
    /**
     * V2ray内部端口（内部监听），v2_port为0时有效
     */
    private Integer v2InsiderPort;
    /**
     * V2ray外部端口（外部覆盖），v2_port为0时有效
     */
    private Integer v2OutsiderPort;

    private Date createdAt;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Object getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(Object isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getV2Id() {
        return v2Id;
    }

    public void setV2Id(String v2Id) {
        this.v2Id = v2Id;
    }

    public Integer getV2AlterId() {
        return v2AlterId;
    }

    public void setV2AlterId(Integer v2AlterId) {
        this.v2AlterId = v2AlterId;
    }

    public Integer getV2Port() {
        return v2Port;
    }

    public void setV2Port(Integer v2Port) {
        this.v2Port = v2Port;
    }

    public String getV2Method() {
        return v2Method;
    }

    public void setV2Method(String v2Method) {
        this.v2Method = v2Method;
    }

    public String getV2Net() {
        return v2Net;
    }

    public void setV2Net(String v2Net) {
        this.v2Net = v2Net;
    }

    public String getV2Type() {
        return v2Type;
    }

    public void setV2Type(String v2Type) {
        this.v2Type = v2Type;
    }

    public String getV2Host() {
        return v2Host;
    }

    public void setV2Host(String v2Host) {
        this.v2Host = v2Host;
    }

    public String getV2Path() {
        return v2Path;
    }

    public void setV2Path(String v2Path) {
        this.v2Path = v2Path;
    }

    public Object getV2Tls() {
        return v2Tls;
    }

    public void setV2Tls(Object v2Tls) {
        this.v2Tls = v2Tls;
    }

    public Integer getV2InsiderPort() {
        return v2InsiderPort;
    }

    public void setV2InsiderPort(Integer v2InsiderPort) {
        this.v2InsiderPort = v2InsiderPort;
    }

    public Integer getV2OutsiderPort() {
        return v2OutsiderPort;
    }

    public void setV2OutsiderPort(Integer v2OutsiderPort) {
        this.v2OutsiderPort = v2OutsiderPort;
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