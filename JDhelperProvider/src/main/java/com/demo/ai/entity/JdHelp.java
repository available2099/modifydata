package com.demo.ai.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * jd_plantBean(JdHelp)实体类
 * @author makejava
 * @since 2020-12-24 16:53:17
 */
@Getter
@Setter
@EqualsAndHashCode
public class JdHelp implements Serializable {
    private static final long serialVersionUID = 285595574033012982L;

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


}