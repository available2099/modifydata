package com.demo.ai.service;

import com.demo.ai.dao.JdPlantbeanDao;
import com.demo.ai.entity.JdPlantbean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * jd_plantBean(JdPlantbean)表服务实现类
 *
 * @author makejava
 * @since 2020-09-09 12:02:39
 */
@Service("jdPlantbeanService")
public class JdPlantbeanServiceImpl implements JdPlantbeanService {
    @Resource
    private JdPlantbeanDao jdPlantbeanDao;

    @Override
    public List<JdPlantbean> queryAll(JdPlantbean jdPlantbean) {
        return jdPlantbeanDao.queryAll(jdPlantbean);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public JdPlantbean queryById(BigInteger id) {
        return this.jdPlantbeanDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<JdPlantbean> queryAllByLimit(int offset, int limit) {
        return this.jdPlantbeanDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param jdPlantbean 实例对象
     * @return 实例对象
     */
    @Override
    public JdPlantbean insert(JdPlantbean jdPlantbean) {
        this.jdPlantbeanDao.insert(jdPlantbean);
        return jdPlantbean;
    }

    /**
     * 修改数据
     *
     * @param jdPlantbean 实例对象
     * @return 实例对象
     */
    @Override
    public JdPlantbean update(JdPlantbean jdPlantbean) {
        this.jdPlantbeanDao.update(jdPlantbean);
        return this.queryById(jdPlantbean.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(BigInteger id) {
        return this.jdPlantbeanDao.deleteById(id) > 0;
    }
}