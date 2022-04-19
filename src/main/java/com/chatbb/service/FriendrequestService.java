package com.chatbb.service;

import com.chatbb.entity.Friendrequest;
import java.util.List;

/**
 * (Friendrequest)表服务接口
 *
 * @author makejava
 * @since 2022-03-31 13:13:15
 */
public interface FriendrequestService {

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    List<Friendrequest> queryById(Integer uid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Friendrequest> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param friendrequest 实例对象
     * @return 实例对象
     */
    String insert(Friendrequest friendrequest);

    /**
     * 修改数据
     *
     * @param friendrequest 实例对象
     * @return 实例对象
     */

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer uid);


    /**
     * 查询是否已经添加为好友了
     * @param friendrequest
     * @return
     */
    boolean queryByUidAndResid(Friendrequest friendrequest);


    /**
     * 拒绝其他用户的申请
     * @param uid
     * @param requestid
     */
    void refuseAdd(Integer uid,Integer requestid);

}