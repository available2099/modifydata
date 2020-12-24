package com.demo.ai.service.impl;

import com.demo.ai.dao.VpsDeviceDao;
import com.demo.ai.entity.VpsDevice;
import com.demo.ai.service.VpsDeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (VpsDevice)表服务实现类
 *
 * @author makejava
 * @since 2020-12-24 16:53:42
 */
@Service("vpsDeviceService")
public class VpsDeviceServiceImpl implements VpsDeviceService {
    @Resource
    private VpsDeviceDao vpsDeviceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public VpsDevice queryById(Integer id) {
        return this.vpsDeviceDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<VpsDevice> queryAllByLimit(int offset, int limit) {
        return this.vpsDeviceDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param vpsDevice 实例对象
     * @return 实例对象
     */
    @Override
    public VpsDevice insert(VpsDevice vpsDevice) {
        this.vpsDeviceDao.insert(vpsDevice);
        return vpsDevice;
    }

    /**
     * 修改数据
     *
     * @param vpsDevice 实例对象
     * @return 实例对象
     */
    @Override
    public VpsDevice update(VpsDevice vpsDevice) {
        this.vpsDeviceDao.update(vpsDevice);
        return this.queryById(vpsDevice.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.vpsDeviceDao.deleteById(id) > 0;
    }
}