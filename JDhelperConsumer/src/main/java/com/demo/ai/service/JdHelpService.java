package com.demo.ai.service;

import com.demo.ai.entity.JdHelp;

import java.math.BigInteger;
import java.util.List;

/**
 * jd_plantBean(JdHelp)表服务接口
 *
 * @author makejava
 * @since 2020-12-24 17:23:50
 */
public interface JdHelpService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    JdHelp queryById(BigInteger id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<JdHelp> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param jdHelp 实例对象
     * @return 实例对象
     */
    JdHelp insert(JdHelp jdHelp);

    /**
     * 修改数据
     *
     * @param jdHelp 实例对象
     * @return 实例对象
     */
    JdHelp update(JdHelp jdHelp);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(BigInteger id);

}