package com.demo.ai.dao;

import com.demo.ai.entity.JdPlantbean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * jd_plantBean(JdPlantbean)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-09 12:02:35
 */
@Mapper
public interface JdPlantbeanDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    JdPlantbean queryById(BigInteger id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<JdPlantbean> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param jdPlantbean 实例对象
     * @return 对象列表
     */
    List<JdPlantbean> queryAll(JdPlantbean jdPlantbean);

    /**
     * 新增数据
     *
     * @param jdPlantbean 实例对象
     * @return 影响行数
     */
    int insert(JdPlantbean jdPlantbean);

    /**
     * 修改数据
     *
     * @param jdPlantbean 实例对象
     * @return 影响行数
     */
    int update(JdPlantbean jdPlantbean);
    int updateAll(JdPlantbean jdPlantbean);
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(BigInteger id);

}