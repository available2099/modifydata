package com.demo.ai.service;

import com.demo.ai.entity.JdHelpUrl;

import java.math.BigInteger;
import java.util.List;

/**
 * jd_plantBean(JdHelpUrl)表服务接口
 *
 * @author makejava
 * @since 2020-12-24 19:43:07
 */
public interface JdHelpUrlService {
    List<JdHelpUrl> queryAll(JdHelpUrl jdHelpUrl);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    JdHelpUrl queryById(BigInteger id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<JdHelpUrl> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param jdHelpUrl 实例对象
     * @return 实例对象
     */
    JdHelpUrl insert(JdHelpUrl jdHelpUrl);

    /**
     * 修改数据
     *
     * @param jdHelpUrl 实例对象
     * @return 实例对象
     */
    JdHelpUrl update(JdHelpUrl jdHelpUrl);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(BigInteger id);

}