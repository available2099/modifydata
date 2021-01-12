package com.demo.ai.service.impl;

import com.demo.ai.dao.JdHelpUrlDao;
import com.demo.ai.entity.JdHelpUrl;
import com.demo.ai.service.JdHelpUrlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * jd_plantBean(JdHelpUrl)表服务实现类
 *
 * @author makejava
 * @since 2020-12-24 22:21:03
 */
@Service("jdHelpUrlService")
public class JdHelpUrlServiceImpl implements JdHelpUrlService {
    @Resource
    private JdHelpUrlDao jdHelpUrlDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public JdHelpUrl queryById(BigInteger id) {
        return this.jdHelpUrlDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<JdHelpUrl> queryAllByLimit(int offset, int limit) {
        return this.jdHelpUrlDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param jdHelpUrl 实例对象
     * @return 实例对象
     */
    @Override
    public JdHelpUrl insert(JdHelpUrl jdHelpUrl) {
        this.jdHelpUrlDao.insert(jdHelpUrl);
        return jdHelpUrl;
    }

    /**
     * 修改数据
     *
     * @param jdHelpUrl 实例对象
     * @return 实例对象
     */
    @Override
    public JdHelpUrl update(JdHelpUrl jdHelpUrl) {
        this.jdHelpUrlDao.update(jdHelpUrl);
        return this.queryById(jdHelpUrl.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(BigInteger id) {
        return this.jdHelpUrlDao.deleteById(id) > 0;
    }

    @Override
    public List<JdHelpUrl> queryAll(JdHelpUrl jdHelpUrl) {
        return jdHelpUrlDao.queryAll(jdHelpUrl);
    }
}