package com.demo.ai.controller;

import com.demo.ai.entity.VpsNode;
import com.demo.ai.service.VpsNodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (VpsNode)表控制层
 *
 * @author makejava
 * @since 2020-12-24 16:53:48
 */
@RestController
@RequestMapping("vpsNode")
public class VpsNodeController {
    /**
     * 服务对象
     */
    @Resource
    private VpsNodeService vpsNodeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public VpsNode selectOne(Integer id) {
        return this.vpsNodeService.queryById(id);
    }

}