package com.demo.ai.service;

import com.demo.ai.entity.Inbound;

import java.util.List;

/**
 * (Inbound)表服务接口
 *
 * @author makejava
 * @since 2020-12-24 17:20:36
 */
public interface InboundService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Inbound queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Inbound> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param inbound 实例对象
     * @return 实例对象
     */
    Inbound insert(Inbound inbound);

    /**
     * 修改数据
     *
     * @param inbound 实例对象
     * @return 实例对象
     */
    Inbound update(Inbound inbound);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}