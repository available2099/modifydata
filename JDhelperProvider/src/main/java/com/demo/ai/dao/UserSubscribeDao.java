package com.demo.ai.dao;

import com.demo.ai.entity.UserSubscribe;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (UserSubscribe)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-24 16:53:34
 */
@Mapper
public interface UserSubscribeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserSubscribe queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserSubscribe> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userSubscribe 实例对象
     * @return 对象列表
     */
    List<UserSubscribe> queryAll(UserSubscribe userSubscribe);

    /**
     * 新增数据
     *
     * @param userSubscribe 实例对象
     * @return 影响行数
     */
    int insert(UserSubscribe userSubscribe);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserSubscribe> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserSubscribe> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserSubscribe> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<UserSubscribe> entities);

    /**
     * 修改数据
     *
     * @param userSubscribe 实例对象
     * @return 影响行数
     */
    int update(UserSubscribe userSubscribe);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}