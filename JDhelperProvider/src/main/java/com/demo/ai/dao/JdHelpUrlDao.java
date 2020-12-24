package com.demo.ai.dao;

import com.demo.ai.entity.JdHelpUrl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * jd_plantBean(JdHelpUrl)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-24 19:43:06
 */
@Mapper
public interface JdHelpUrlDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    JdHelpUrl queryById(BigInteger id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<JdHelpUrl> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param jdHelpUrl 实例对象
     * @return 对象列表
     */
    List<JdHelpUrl> queryAll(JdHelpUrl jdHelpUrl);

    /**
     * 新增数据
     *
     * @param jdHelpUrl 实例对象
     * @return 影响行数
     */
    int insert(JdHelpUrl jdHelpUrl);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<JdHelpUrl> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<JdHelpUrl> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<JdHelpUrl> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<JdHelpUrl> entities);

    /**
     * 修改数据
     *
     * @param jdHelpUrl 实例对象
     * @return 影响行数
     */
    int update(JdHelpUrl jdHelpUrl);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(BigInteger id);

}