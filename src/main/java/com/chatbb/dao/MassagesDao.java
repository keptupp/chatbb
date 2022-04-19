package com.chatbb.dao;

import com.chatbb.entity.Massages;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Massages)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-02 21:33:03
 */
@Mapper
public interface MassagesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param mid 主键
     * @return 实例对象
     */
    Massages queryById(Integer mid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Massages> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param massages 实例对象
     * @return 对象列表
     */
    List<Massages> queryAll(Massages massages);

    /**
     * 获取用户于好友最后一条的消息，用于在展示好友列表时，展示最后一条消息
     * @param uid
     * @param fid
     * @return
     */
    Massages queryLastMessage(Integer uid,Integer fid);



    /**
     * 新增数据
     *
     * @param massages 实例对象
     * @return 影响行数
     */
    int insert(Massages massages);

    /**
     * 修改数据
     *
     * @param massages 实例对象
     * @return 影响行数
     */
    int update(Massages massages);

    /**
     * 通过主键删除数据
     *
     * @param mid 主键
     * @return 影响行数
     */
    int deleteById(Integer mid);


    /**
     * 查询二者的聊天记录
     * @param userid
     * @param friendid
     * @return
     */
    List<Massages> requestMassage(Integer userid,Integer friendid);


}