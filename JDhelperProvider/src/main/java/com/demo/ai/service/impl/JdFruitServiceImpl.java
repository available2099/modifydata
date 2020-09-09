package com.demo.ai.service.impl;

import com.demo.ai.dao.JdFruitDao;
import com.demo.ai.entity.JdFruit;
import com.demo.ai.service.JdFruitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * jd_plantBean(JdFruit)表服务实现类
 *
 * @author makejava
 * @since 2020-09-09 12:02:18
 */
@Service("jdFruitService")
public class JdFruitServiceImpl implements JdFruitService {
    @Resource
    private JdFruitDao jdFruitDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public JdFruit queryById(BigInteger id) {
        return this.jdFruitDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<JdFruit> queryAllByLimit(int offset, int limit) {
        return this.jdFruitDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param jdFruit 实例对象
     * @return 实例对象
     */
    @Override
    public JdFruit insert(JdFruit jdFruit) {
        this.jdFruitDao.insert(jdFruit);
        return jdFruit;
    }

    /**
     * 修改数据
     *
     * @param jdFruit 实例对象
     * @return 实例对象
     */
    @Override
    public JdFruit update(JdFruit jdFruit) {
        this.jdFruitDao.update(jdFruit);
        return this.queryById(jdFruit.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(BigInteger id) {
        return this.jdFruitDao.deleteById(id) > 0;
    }
}