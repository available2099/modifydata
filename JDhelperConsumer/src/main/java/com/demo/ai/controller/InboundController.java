package com.demo.ai.controller;

import com.demo.ai.entity.Inbound;
import com.demo.ai.service.InboundService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Inbound)表控制层
 *
 * @author makejava
 * @since 2020-12-24 17:20:40
 */
@RestController
@RequestMapping("inbound")
public class InboundController {
    /**
     * 服务对象
     */
    @Resource
    private InboundService inboundService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Inbound selectOne(Integer id) {
        return this.inboundService.queryById(id);
    }

}