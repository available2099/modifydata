package com.demo.ai.service;

import com.demo.ai.entity.JdFruit;

import java.math.BigInteger;
import java.util.List;

/**
 * jd_plantBean(JdFruit)表服务接口
 *
 * @author makejava
 * @since 2020-09-06 17:14:21
 */
public interface JdFruitService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    JdFruit queryById(BigInteger id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<JdFruit> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param jdFruit 实例对象
     * @return 实例对象
     */
    JdFruit insert(JdFruit jdFruit);

    /**
     * 修改数据
     *
     * @param jdFruit 实例对象
     * @return 实例对象
     */
    JdFruit update(JdFruit jdFruit);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(BigInteger id);

}