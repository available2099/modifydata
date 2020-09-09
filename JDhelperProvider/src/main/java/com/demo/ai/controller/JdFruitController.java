package com.demo.ai.controller;

import com.demo.ai.entity.JdFruit;
import com.demo.ai.service.JdFruitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * jd_plantBean(JdFruit)表控制层
 *
 * @author makejava
 * @since 2020-09-09 12:02:20
 */
@RestController
@RequestMapping("jdFruit")
public class JdFruitController {
    /**
     * 服务对象
     */
    @Resource
    private JdFruitService jdFruitService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public JdFruit selectOne(BigInteger id) {
        return this.jdFruitService.queryById(id);
    }

}