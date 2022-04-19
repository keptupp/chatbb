package com.chatbb.dao;

import com.chatbb.entity.Friendstable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Friendstable)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-31 23:11:24
 */
@Mapper
public interface FriendstableDao {


    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Friendstable> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param friendstable 实例对象
     * @return 对象列表
     */
    List<Friendstable> queryAll(Friendstable friendstable);

    /**
     * 新增数据
     *
     * @param friendstable 实例对象
     * @return 影响行数
     */
    int insert(Friendstable friendstable);


    /**
     * 用于检测friendid是不是myid的好友
     * @param myid
     * @param friendid
     * @return
     */
    Boolean isFriend(Integer myid,Integer friendid);


    /**
     * 查询好友的id集合
     * @param uid
     * @return
     */
    List<Integer> queryFidByuid(Integer uid);

    /**
     * 更新好友之间为读消息的条数
     * @param userid
     * @param friendid
     * @param unreadnum
     */
    void updateUnReadNum(Integer userid,Integer friendid,Integer unreadnum);


    /**
     * 删除好友，单项删除
     * @param userid
     * @param friendid
     */
    void deleteFriend(Integer userid,Integer friendid);
}