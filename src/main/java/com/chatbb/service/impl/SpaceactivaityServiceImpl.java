package com.chatbb.service.impl;

import com.chatbb.entity.Spaceactivaity;
import com.chatbb.dao.SpaceactivaityDao;
import com.chatbb.service.SpaceactivaityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Spaceactivaity)表服务实现类
 *
 * @author makejava
 * @since 2022-04-19 16:09:15
 */
@Service("spaceactivaityService")
public class SpaceactivaityServiceImpl implements SpaceactivaityService {
    @Resource
    private SpaceactivaityDao spaceactivaityDao;

    /**
     * 通过ID查询单条数据
     *
     * @param said 主键
     * @return 实例对象
     */
    @Override
    public Spaceactivaity queryById(Integer said) {
        return this.spaceactivaityDao.queryById(said);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Spaceactivaity> queryAllByLimit(int offset, int limit) {
        return this.spaceactivaityDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param spaceactivaity 实例对象
     * @return 实例对象
     */
    @Override
    public Spaceactivaity insert(Spaceactivaity spaceactivaity) {
        this.spaceactivaityDao.insert(spaceactivaity);
        return spaceactivaity;
    }

    /**
     * 修改数据
     *
     * @param spaceactivaity 实例对象
     * @return 实例对象
     */
    @Override
    public Spaceactivaity update(Spaceactivaity spaceactivaity) {
        this.spaceactivaityDao.update(spaceactivaity);
        return this.queryById(spaceactivaity.getSaid());
    }

    /**
     * 通过主键删除数据
     *
     * @param said 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer said) {
        return this.spaceactivaityDao.deleteById(said) > 0;
    }
}