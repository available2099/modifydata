package com.demo.ai.dao;

import com.demo.ai.entity.FailedNodeJobs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 失败任务(FailedNodeJobs)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-24 16:53:09
 */
@Mapper
public interface FailedNodeJobsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FailedNodeJobs queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<FailedNodeJobs> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param failedNodeJobs 实例对象
     * @return 对象列表
     */
    List<FailedNodeJobs> queryAll(FailedNodeJobs failedNodeJobs);

    /**
     * 新增数据
     *
     * @param failedNodeJobs 实例对象
     * @return 影响行数
     */
    int insert(FailedNodeJobs failedNodeJobs);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<FailedNodeJobs> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<FailedNodeJobs> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<FailedNodeJobs> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<FailedNodeJobs> entities);

    /**
     * 修改数据
     *
     * @param failedNodeJobs 实例对象
     * @return 影响行数
     */
    int update(FailedNodeJobs failedNodeJobs);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}