package com.demo.ai.service;

import com.demo.ai.entity.JdPlantbean;

import java.math.BigInteger;
import java.util.List;

/**
 * jd_plantBean(JdPlantbean)表服务接口
 *
 * @author makejava
 * @since 2020-09-09 12:02:37
 */
public interface JdPlantbeanService {
    /**
     * 通过实体作为筛选条件查询
     *
     * @param jdPlantbean 实例对象
     * @return 对象列表
     */
    List<JdPlantbean> queryAll(JdPlantbean jdPlantbean);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    JdPlantbean queryById(BigInteger id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<JdPlantbean> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param jdPlantbean 实例对象
     * @return 实例对象
     */
    JdPlantbean insert(JdPlantbean jdPlantbean);

    /**
     * 修改数据
     *
     * @param jdPlantbean 实例对象
     * @return 实例对象
     */
    JdPlantbean update(JdPlantbean jdPlantbean);
    void updateAll(JdPlantbean jdPlantbean);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(BigInteger id);

}