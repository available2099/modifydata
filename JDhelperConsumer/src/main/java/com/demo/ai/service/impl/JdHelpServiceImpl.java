package com.demo.ai.service.impl;

import com.demo.ai.dao.JdHelpDao;
import com.demo.ai.entity.JdHelp;
import com.demo.ai.service.JdHelpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * jd_plantBean(JdHelp)表服务实现类
 *
 * @author makejava
 * @since 2020-12-24 17:23:52
 */
@Service("jdHelpService")
public class JdHelpServiceImpl implements JdHelpService {
    @Resource
    private JdHelpDao jdHelpDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public JdHelp queryById(BigInteger id) {
        return this.jdHelpDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<JdHelp> queryAllByLimit(int offset, int limit) {
        return this.jdHelpDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param jdHelp 实例对象
     * @return 实例对象
     */
    @Override
    public JdHelp insert(JdHelp jdHelp) {
        this.jdHelpDao.insert(jdHelp);
        return jdHelp;
    }

    /**
     * 修改数据
     *
     * @param jdHelp 实例对象
     * @return 实例对象
     */
    @Override
    public JdHelp update(JdHelp jdHelp) {
        this.jdHelpDao.update(jdHelp);
        return this.queryById(jdHelp.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(BigInteger id) {
        return this.jdHelpDao.deleteById(id) > 0;
    }
}