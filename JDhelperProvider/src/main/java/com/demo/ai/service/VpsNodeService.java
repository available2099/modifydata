package com.demo.ai.service;

import com.demo.ai.entity.VpsNode;

import java.util.List;

/**
 * (VpsNode)表服务接口
 *
 * @author makejava
 * @since 2020-12-24 16:53:47
 */
public interface VpsNodeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VpsNode queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<VpsNode> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param vpsNode 实例对象
     * @return 实例对象
     */
    VpsNode insert(VpsNode vpsNode);

    /**
     * 修改数据
     *
     * @param vpsNode 实例对象
     * @return 实例对象
     */
    VpsNode update(VpsNode vpsNode);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}