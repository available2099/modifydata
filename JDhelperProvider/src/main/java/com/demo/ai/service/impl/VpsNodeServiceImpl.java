package com.demo.ai.service.impl;

import com.demo.ai.dao.VpsNodeDao;
import com.demo.ai.entity.VpsNode;
import com.demo.ai.service.VpsNodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (VpsNode)表服务实现类
 *
 * @author makejava
 * @since 2020-12-24 16:53:47
 */
@Service("vpsNodeService")
public class VpsNodeServiceImpl implements VpsNodeService {
    @Resource
    private VpsNodeDao vpsNodeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public VpsNode queryById(Integer id) {
        return this.vpsNodeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<VpsNode> queryAllByLimit(int offset, int limit) {
        return this.vpsNodeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param vpsNode 实例对象
     * @return 实例对象
     */
    @Override
    public VpsNode insert(VpsNode vpsNode) {
        this.vpsNodeDao.insert(vpsNode);
        return vpsNode;
    }

    /**
     * 修改数据
     *
     * @param vpsNode 实例对象
     * @return 实例对象
     */
    @Override
    public VpsNode update(VpsNode vpsNode) {
        this.vpsNodeDao.update(vpsNode);
        return this.queryById(vpsNode.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.vpsNodeDao.deleteById(id) > 0;
    }
}