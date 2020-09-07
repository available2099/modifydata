package com.demo.ai.controller;

import com.demo.ai.entity.JdPlantbean;
import com.demo.ai.service.JdPlantbeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

/**
 * jd_plantBean(JdPlantbean)表控制层
 *
 * @author makejava
 * @since 2020-09-06 17:14:34
 */
@RestController
@RequestMapping("jdPlantbean")
public class JdPlantbeanController {
    /**
     * 服务对象
     */
    @Autowired
    private JdPlantbeanService jdPlantbeanService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public JdPlantbean selectOne(BigInteger id) {
        return this.jdPlantbeanService.queryById(id);
    }

}