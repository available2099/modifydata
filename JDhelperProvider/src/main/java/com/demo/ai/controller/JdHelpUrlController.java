package com.demo.ai.controller;

import com.demo.ai.entity.JdHelpUrl;
import com.demo.ai.service.JdHelpUrlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * jd_plantBean(JdHelpUrl)表控制层
 *
 * @author makejava
 * @since 2020-12-24 19:43:11
 */
@RestController
@RequestMapping("jdHelpUrl")
public class JdHelpUrlController {
    /**
     * 服务对象
     */
    @Resource
    private JdHelpUrlService jdHelpUrlService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public JdHelpUrl selectOne(BigInteger id) {
        return this.jdHelpUrlService.queryById(id);
    }

}