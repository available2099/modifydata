package com.demo.ai.service;

import com.demo.ai.entity.JdMobilecity;

import java.math.BigInteger;
import java.util.List;

/**
 * jd_plantBean(JdMobilecity)表服务接口
 *
 * @author makejava
 * @since 2020-10-21 15:32:46
 */
public interface JdMobilecityService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    JdMobilecity queryById(BigInteger id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<JdMobilecity> queryAllByLimit(int offset, int limit);
    List<JdMobilecity> queryAll(JdMobilecity se);

    /**
     * 新增数据
     *
     * @param jdMobilecity 实例对象
     * @return 实例对象
     */
    JdMobilecity insert(JdMobilecity jdMobilecity);

    /**
     * 修改数据
     *
     * @param jdMobilecity 实例对象
     * @return 实例对象
     */
    JdMobilecity update(JdMobilecity jdMobilecity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(BigInteger id);

}