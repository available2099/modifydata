package com.demo.ai.service.impl;

import com.demo.ai.dao.FailedNodeJobsDao;
import com.demo.ai.entity.FailedNodeJobs;
import com.demo.ai.service.FailedNodeJobsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 失败任务(FailedNodeJobs)表服务实现类
 *
 * @author makejava
 * @since 2020-12-24 17:17:47
 */
@Service("failedNodeJobsService")
public class FailedNodeJobsServiceImpl implements FailedNodeJobsService {
    @Resource
    private FailedNodeJobsDao failedNodeJobsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public FailedNodeJobs queryById(Long id) {
        return this.failedNodeJobsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<FailedNodeJobs> queryAllByLimit(int offset, int limit) {
        return this.failedNodeJobsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param failedNodeJobs 实例对象
     * @return 实例对象
     */
    @Override
    public FailedNodeJobs insert(FailedNodeJobs failedNodeJobs) {
        this.failedNodeJobsDao.insert(failedNodeJobs);
        return failedNodeJobs;
    }

    /**
     * 修改数据
     *
     * @param failedNodeJobs 实例对象
     * @return 实例对象
     */
    @Override
    public FailedNodeJobs update(FailedNodeJobs failedNodeJobs) {
        this.failedNodeJobsDao.update(failedNodeJobs);
        return this.queryById(failedNodeJobs.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.failedNodeJobsDao.deleteById(id) > 0;
    }
}