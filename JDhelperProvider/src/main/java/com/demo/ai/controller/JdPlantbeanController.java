package com.demo.ai.controller;

import com.demo.ai.entity.JdPlantbean;
import com.demo.ai.service.JdPlantbeanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * jd_plantBean(JdPlantbean)表控制层
 *
 * @author makejava
 * @since 2020-09-09 12:02:43
 */
@RestController
@RequestMapping("jdPlantbean")
public class JdPlantbeanController {
    /**
     * 服务对象
     */
    @Resource
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