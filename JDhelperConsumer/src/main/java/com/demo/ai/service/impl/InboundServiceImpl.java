package com.demo.ai.service.impl;

import com.demo.ai.dao.InboundDao;
import com.demo.ai.entity.Inbound;
import com.demo.ai.service.InboundService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Inbound)表服务实现类
 *
 * @author makejava
 * @since 2020-12-24 17:20:37
 */
@Service("inboundService")
public class InboundServiceImpl implements InboundService {
    @Resource
    private InboundDao inboundDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Inbound queryById(Integer id) {
        return this.inboundDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Inbound> queryAllByLimit(int offset, int limit) {
        return this.inboundDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param inbound 实例对象
     * @return 实例对象
     */
    @Override
    public Inbound insert(Inbound inbound) {
        this.inboundDao.insert(inbound);
        return inbound;
    }

    /**
     * 修改数据
     *
     * @param inbound 实例对象
     * @return 实例对象
     */
    @Override
    public Inbound update(Inbound inbound) {
        this.inboundDao.update(inbound);
        return this.queryById(inbound.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.inboundDao.deleteById(id) > 0;
    }
}