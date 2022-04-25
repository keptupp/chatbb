package com.chatbb.dao;

import com.chatbb.entity.Likerecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Likerecord)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-20 10:56:33
 */
@Mapper
public interface LikerecordDao {

    /**
     * 通过ID查询单条数据
     *
     * @param lrid 主键
     * @return 实例对象
     */
    Likerecord queryById(Integer lrid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Likerecord> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param likerecord 实例对象
     * @return 对象列表
     */
    List<Likerecord> queryAll(Likerecord likerecord);

    /**
     * 新增数据
     *
     * @param likerecord 实例对象
     * @return 影响行数
     */
    int insert(Likerecord likerecord);

    /**
     * 修改数据
     *
     * @param likerecord 实例对象
     * @return 影响行数
     */
    int update(Likerecord likerecord);

    /**
     * 通过主键删除数据
     *
     * @param lrid 主键
     * @return 影响行数
     */
    int deleteById(Integer lrid);


    /**
     * 根据空间id去点赞评论中返回该空间的记录
     * @param said
     * @return
     */
    List<Likerecord> queryBySaid(Integer said);

    /**
     * 查询该用户是否已经有记录了
     * @param said
     * @param userid
     * @return
     */
    Integer isRecord(Integer said,Integer userid);


    /**
     * 更新用户的评论
     * @param said
     * @param userid
     * @param record
     */
    void updateBySaidUserid(Integer said,Integer userid,String record);


    /**
     * 新增用户评价记录
     * @param said
     * @param userid
     * @param record
     */
    void insertRecord(Integer said,Integer userid,String record);

    /**
     * 查询是否有该用户的评价记录
     * @param said
     * @param userid
     * @return
     */
    Likerecord isLike(Integer said,Integer userid);


    /**
     * 新增点赞
     * @param said
     * @param userid
     * @param islike
     */
    void insertLike(Integer said,Integer userid,boolean islike);


    /**
     * 更新点赞状态，点赞或取消点赞
     * @param said
     * @param userid
     * @param islike
     */
    void updateLike(Integer said,Integer userid,boolean islike);

}