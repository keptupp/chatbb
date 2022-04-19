package com.chatbb.service;

import com.chatbb.entity.Massages;
import java.util.List;

/**
 * (Massages)表服务接口
 *
 * @author makejava
 * @since 2022-04-02 21:33:03
 */
public interface MassagesService {

    /**
     * 通过ID查询单条数据
     *
     * @param mid 主键
     * @return 实例对象
     */
    Massages queryById(Integer mid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Massages> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param massages 实例对象
     * @return 实例对象
     */
    Massages insert(Massages massages);

    /**
     * 修改数据
     *
     * @param massages 实例对象
     * @return 实例对象
     */
    Massages update(Massages massages);

    /**
     * 通过主键删除数据
     *
     * @param mid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer mid);


    /**
     * 查询二者聊天记录
     * @param userid
     * @param friendid
     * @return
     */
    List<Massages> requestMassage(Integer userid,Integer friendid);

}