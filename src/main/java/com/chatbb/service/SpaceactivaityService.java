package com.chatbb.service;

import com.chatbb.entity.Spaceactivaity;
import java.util.List;

/**
 * (Spaceactivaity)表服务接口
 *
 * @author makejava
 * @since 2022-04-19 16:09:15
 */
public interface SpaceactivaityService {

    /**
     * 通过ID查询单条数据
     *
     * @param said 主键
     * @return 实例对象
     */
    Spaceactivaity queryById(Integer said);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Spaceactivaity> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param spaceactivaity 实例对象
     * @return 实例对象
     */
    Spaceactivaity insert(Spaceactivaity spaceactivaity);

    /**
     * 修改数据
     *
     * @param spaceactivaity 实例对象
     * @return 实例对象
     */
    Spaceactivaity update(Spaceactivaity spaceactivaity);

    /**
     * 通过主键删除数据
     *
     * @param said 主键
     * @return 是否成功
     */
    boolean deleteById(Integer said);

}