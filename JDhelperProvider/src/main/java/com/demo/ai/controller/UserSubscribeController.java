package com.demo.ai.controller;

import com.demo.ai.entity.UserSubscribe;
import com.demo.ai.service.UserSubscribeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (UserSubscribe)表控制层
 *
 * @author makejava
 * @since 2020-12-24 16:53:36
 */
@RestController
@RequestMapping("userSubscribe")
public class UserSubscribeController {
    /**
     * 服务对象
     */
    @Resource
    private UserSubscribeService userSubscribeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserSubscribe selectOne(Integer id) {
        return this.userSubscribeService.queryById(id);
    }

}