package com.demo.ai.controller;

import com.demo.ai.entity.VpsDevice;
import com.demo.ai.service.VpsDeviceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (VpsDevice)表控制层
 *
 * @author makejava
 * @since 2020-12-24 16:53:43
 */
@RestController
@RequestMapping("vpsDevice")
public class VpsDeviceController {
    /**
     * 服务对象
     */
    @Resource
    private VpsDeviceService vpsDeviceService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public VpsDevice selectOne(Integer id) {
        return this.vpsDeviceService.queryById(id);
    }

}