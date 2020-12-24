package com.demo.ai.dao;

import com.demo.ai.entity.Inbound;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Inbound)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-24 17:20:35
 */
public interface InboundDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Inbound queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Inbound> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param inbound 实例对象
     * @return 对象列表
     */
    List<Inbound> queryAll(Inbound inbound);

    /**
     * 新增数据
     *
     * @param inbound 实例对象
     * @return 影响行数
     */
    int insert(Inbound inbound);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Inbound> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Inbound> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Inbound> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Inbound> entities);

    /**
     * 修改数据
     *
     * @param inbound 实例对象
     * @return 影响行数
     */
    int update(Inbound inbound);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}