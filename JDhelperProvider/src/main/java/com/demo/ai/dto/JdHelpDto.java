package com.demo.ai.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * jd_plantBean(JdHelp)实体类
 * @author makejava
 * @since 2020-12-24 16:53:17
 */
@Getter
@Setter
@EqualsAndHashCode
public class JdHelpDto implements Serializable {
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

}