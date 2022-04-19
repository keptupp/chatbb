package com.chatbb.dao;

import com.chatbb.entity.Friendrequest;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Friendrequest)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-31 13:13:15
 */
@Mapper
public interface FriendrequestDao {

    /**
     * 通过ID查询多条数据
     *
     * @return 实例对象
     */
    List<Friendrequest> queryById(Integer uid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Friendrequest> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param friendrequest 实例对象
     * @return 对象列表
     */
    List<Friendrequest> queryAll(Friendrequest friendrequest);

    /**
     * 新增数据
     *
     * @param friendrequest 实例对象
     * @return 影响行数
     */
    int insert(Friendrequest friendrequest);

    /**
     * 修改数据
     *
     * @param friendrequest 实例对象
     * @return 影响行数
     */
    int update(Friendrequest friendrequest);

    /**
     * 通过主键删除数据
     *
     * @return 影响行数
     */
    int deleteById(Integer uid);


    /**
     * 查询是否已经请求添加为好友
     * @param friendrequest
     * @return
     */
    Integer queryByUidAndResid(Friendrequest friendrequest);


    /**
     * 删除一个请求
     * @param uid
     * @param requestid
     */
    void deleteRequest(Integer uid,Integer requestid);

}