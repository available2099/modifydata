package com.demo.ai.service;

import com.demo.ai.entity.FailedNodeJobs;

import java.util.List;

/**
 * 失败任务(FailedNodeJobs)表服务接口
 *
 * @author makejava
 * @since 2020-12-24 17:17:46
 */
public interface FailedNodeJobsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FailedNodeJobs queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<FailedNodeJobs> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param failedNodeJobs 实例对象
     * @return 实例对象
     */
    FailedNodeJobs insert(FailedNodeJobs failedNodeJobs);

    /**
     * 修改数据
     *
     * @param failedNodeJobs 实例对象
     * @return 实例对象
     */
    FailedNodeJobs update(FailedNodeJobs failedNodeJobs);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}