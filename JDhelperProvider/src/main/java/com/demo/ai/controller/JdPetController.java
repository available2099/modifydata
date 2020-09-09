package com.demo.ai.controller;

import com.demo.ai.entity.JdPet;
import com.demo.ai.service.JdPetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * jd_plantBean(JdPet)表控制层
 *
 * @author makejava
 * @since 2020-09-09 12:02:30
 */
@RestController
@RequestMapping("jdPet")
public class JdPetController {
    /**
     * 服务对象
     */
    @Resource
    private JdPetService jdPetService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public JdPet selectOne(BigInteger id) {
        return this.jdPetService.queryById(id);
    }

}