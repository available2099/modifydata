package com.demo.ai.service;

import com.demo.ai.entity.UserSubscribe;

import java.util.List;

/**
 * (UserSubscribe)表服务接口
 *
 * @author makejava
 * @since 2020-12-24 16:53:35
 */
public interface UserSubscribeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserSubscribe queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserSubscribe> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userSubscribe 实例对象
     * @return 实例对象
     */
    UserSubscribe insert(UserSubscribe userSubscribe);

    /**
     * 修改数据
     *
     * @param userSubscribe 实例对象
     * @return 实例对象
     */
    UserSubscribe update(UserSubscribe userSubscribe);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}