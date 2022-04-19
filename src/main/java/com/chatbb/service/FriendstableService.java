package com.chatbb.service;

import com.chatbb.entity.Friendstable;
import java.util.List;

/**
 * (Friendstable)表服务接口
 *
 * @author makejava
 * @since 2022-03-31 23:11:25
 */
public interface FriendstableService {


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Friendstable> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>
     *
     * @param
     * @return 实例对象
     */
    void insert(String myid,String friendid);

    /**
     * 根据用户id返回其好友id，用于向该用户的好友推送他是否在线
     * @param id
     * @return
     */
    List<Integer> queryFidByuid(String id);


    /**
     * 更新好友之间未读消息的条数
     * @param userid
     * @param friendid
     * @param unreadnum
     */
    void updateUnReadNum(Integer userid,Integer friendid,Integer unreadnum);


    /**
     * 删除好友，单向删除
     * @param userid
     * @param friendid
     */
    void deleteFriend(Integer userid,Integer friendid);


}