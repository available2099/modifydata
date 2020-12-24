package com.demo.ai.controller;

import com.demo.ai.entity.JdHelp;
import com.demo.ai.service.JdHelpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * jd_plantBean(JdHelp)表控制层
 *
 * @author makejava
 * @since 2020-12-24 16:53:28
 */
@RestController
@RequestMapping("jdHelp")
public class JdHelpController {
    /**
     * 服务对象
     */
    @Resource
    private JdHelpService jdHelpService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public JdHelp selectOne(BigInteger id) {
        return this.jdHelpService.queryById(id);
    }

}