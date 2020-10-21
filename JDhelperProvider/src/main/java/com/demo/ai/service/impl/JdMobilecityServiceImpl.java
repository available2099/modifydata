package com.demo.ai.service.impl;

import com.demo.ai.dao.JdMobilecityDao;
import com.demo.ai.entity.JdMobilecity;
import com.demo.ai.service.JdMobilecityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * jd_plantBean(JdMobilecity)表服务实现类
 *
 * @author makejava
 * @since 2020-10-21 15:30:34
 */
@Service("jdMobilecityService")
public class JdMobilecityServiceImpl implements JdMobilecityService {
    @Resource
    private JdMobilecityDao jdMobilecityDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public JdMobilecity queryById(BigInteger id) {
        return this.jdMobilecityDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<JdMobilecity> queryAllByLimit(int offset, int limit) {
        return this.jdMobilecityDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param jdMobilecity 实例对象
     * @return 实例对象
     */
    @Override
    public JdMobilecity insert(JdMobilecity jdMobilecity) {
        this.jdMobilecityDao.insert(jdMobilecity);
        return jdMobilecity;
    }

    /**
     * 修改数据
     *
     * @param jdMobilecity 实例对象
     * @return 实例对象
     */
    @Override
    public JdMobilecity update(JdMobilecity jdMobilecity) {
        this.jdMobilecityDao.update(jdMobilecity);
        return this.queryById(jdMobilecity.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(BigInteger id) {
        return this.jdMobilecityDao.deleteById(id) > 0;
    }
}