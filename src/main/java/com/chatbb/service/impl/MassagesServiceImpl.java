package com.chatbb.service.impl;

import com.chatbb.entity.Massages;
import com.chatbb.dao.MassagesDao;
import com.chatbb.service.MassagesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Massages)表服务实现类
 *
 * @author makejava
 * @since 2022-04-02 21:33:03
 */
@Service("massagesService")
public class MassagesServiceImpl implements MassagesService {
    @Resource
    private MassagesDao massagesDao;

    /**
     * 通过ID查询单条数据
     *
     * @param mid 主键
     * @return 实例对象
     */
    @Override
    public Massages queryById(Integer mid) {
        return this.massagesDao.queryById(mid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Massages> queryAllByLimit(int offset, int limit) {
        return this.massagesDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param massages 实例对象
     * @return 实例对象
     */
    @Override
    public Massages insert(Massages massages) {
        this.massagesDao.insert(massages);
        return massages;
    }

    /**
     * 修改数据
     *
     * @param massages 实例对象
     * @return 实例对象
     */
    @Override
    public Massages update(Massages massages) {
        this.massagesDao.update(massages);
        return this.queryById(massages.getMid());
    }

    /**
     * 通过主键删除数据
     *
     * @param mid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer mid) {
        return this.massagesDao.deleteById(mid) > 0;
    }

    /**
     * 查询二者聊天记录
     *
     * @param userid
     * @param friendid
     * @return
     */
    @Override
    public List<Massages> requestMassage(Integer userid, Integer friendid) {
        return massagesDao.requestMassage(userid,friendid);
    }
}