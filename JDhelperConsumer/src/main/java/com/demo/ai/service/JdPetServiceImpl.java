package com.demo.ai.service;

import com.demo.ai.dao.JdPetDao;
import com.demo.ai.entity.JdPet;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * jd_plantBean(JdPet)表服务实现类
 *
 * @author makejava
 * @since 2020-09-09 12:02:28
 */
@Service("jdPetService")
public class JdPetServiceImpl implements JdPetService {
    @Resource
    private JdPetDao jdPetDao;

    @Override
    public List<JdPet> queryAll(JdPet jdPet) {
        return jdPetDao.queryAll(jdPet);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public JdPet queryById(BigInteger id) {
        return this.jdPetDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<JdPet> queryAllByLimit(int offset, int limit) {
        return this.jdPetDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param jdPet 实例对象
     * @return 实例对象
     */
    @Override
    public JdPet insert(JdPet jdPet) {
        this.jdPetDao.insert(jdPet);
        return jdPet;
    }

    /**
     * 修改数据
     *
     * @param jdPet 实例对象
     * @return 实例对象
     */
    @Override
    public JdPet update(JdPet jdPet) {
        this.jdPetDao.update(jdPet);
        return this.queryById(jdPet.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(BigInteger id) {
        return this.jdPetDao.deleteById(id) > 0;
    }
}