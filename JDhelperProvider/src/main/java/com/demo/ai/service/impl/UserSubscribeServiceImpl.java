package com.demo.ai.service.impl;

import com.demo.ai.dao.UserSubscribeDao;
import com.demo.ai.entity.UserSubscribe;
import com.demo.ai.service.UserSubscribeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserSubscribe)表服务实现类
 *
 * @author makejava
 * @since 2020-12-24 16:53:35
 */
@Service("userSubscribeService")
public class UserSubscribeServiceImpl implements UserSubscribeService {
    @Resource
    private UserSubscribeDao userSubscribeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserSubscribe queryById(Integer id) {
        return this.userSubscribeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<UserSubscribe> queryAllByLimit(int offset, int limit) {
        return this.userSubscribeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userSubscribe 实例对象
     * @return 实例对象
     */
    @Override
    public UserSubscribe insert(UserSubscribe userSubscribe) {
        this.userSubscribeDao.insert(userSubscribe);
        return userSubscribe;
    }

    /**
     * 修改数据
     *
     * @param userSubscribe 实例对象
     * @return 实例对象
     */
    @Override
    public UserSubscribe update(UserSubscribe userSubscribe) {
        this.userSubscribeDao.update(userSubscribe);
        return this.queryById(userSubscribe.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userSubscribeDao.deleteById(id) > 0;
    }
}