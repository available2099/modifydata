package com.demo.ai.controller;

import com.demo.ai.entity.FailedNodeJobs;
import com.demo.ai.service.FailedNodeJobsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 失败任务(FailedNodeJobs)表控制层
 *
 * @author makejava
 * @since 2020-12-24 16:53:10
 */
@RestController
@RequestMapping("failedNodeJobs")
public class FailedNodeJobsController {
    /**
     * 服务对象
     */
    @Resource
    private FailedNodeJobsService failedNodeJobsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public FailedNodeJobs selectOne(Long id) {
        return this.failedNodeJobsService.queryById(id);
    }

}