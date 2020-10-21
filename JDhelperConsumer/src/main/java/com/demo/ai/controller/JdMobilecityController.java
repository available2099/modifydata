package com.demo.ai.controller;

import com.demo.ai.entity.JdMobilecity;
import com.demo.ai.service.JdMobilecityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * jd_plantBean(JdMobilecity)表控制层
 *
 * @author makejava
 * @since 2020-10-21 15:32:51
 */
@RestController
@RequestMapping("jdMobilecity")
public class JdMobilecityController {
    /**
     * 服务对象
     */
    @Resource
    private JdMobilecityService jdMobilecityService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public JdMobilecity selectOne(BigInteger id) {
        return this.jdMobilecityService.queryById(id);
    }

}