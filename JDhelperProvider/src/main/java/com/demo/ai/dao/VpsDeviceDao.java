package com.demo.ai.dao;

import com.demo.ai.entity.VpsDevice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (VpsDevice)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-24 16:53:40
 */
@Mapper
public interface VpsDeviceDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VpsDevice queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<VpsDevice> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param vpsDevice 实例对象
     * @return 对象列表
     */
    List<VpsDevice> queryAll(VpsDevice vpsDevice);

    /**
     * 新增数据
     *
     * @param vpsDevice 实例对象
     * @return 影响行数
     */
    int insert(VpsDevice vpsDevice);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<VpsDevice> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<VpsDevice> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<VpsDevice> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<VpsDevice> entities);

    /**
     * 修改数据
     *
     * @param vpsDevice 实例对象
     * @return 影响行数
     */
    int update(VpsDevice vpsDevice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}