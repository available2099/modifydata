package com.demo.ai.service;

import com.demo.ai.entity.JdPet;

import java.math.BigInteger;
import java.util.List;

/**
 * jd_plantBean(JdPet)表服务接口
 *
 * @author makejava
 * @since 2020-09-09 12:02:26
 */
public interface JdPetService {
    /**
     * 通过实体作为筛选条件查询
     *
     * @param jdPet 实例对象
     * @return 对象列表
     */
    List<JdPet> queryAll(JdPet jdPet);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    JdPet queryById(BigInteger id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<JdPet> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param jdPet 实例对象
     * @return 实例对象
     */
    JdPet insert(JdPet jdPet);

    /**
     * 修改数据
     *
     * @param jdPet 实例对象
     * @return 实例对象
     */
    JdPet update(JdPet jdPet);
    void updateAll(JdPet jdPet);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(BigInteger id);

}