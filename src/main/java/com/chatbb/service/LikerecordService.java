package com.chatbb.service;

import com.chatbb.entity.Likerecord;
import java.util.List;

/**
 * (Likerecord)表服务接口
 *
 * @author makejava
 * @since 2022-04-20 10:56:33
 */
public interface LikerecordService {

    /**
     * 通过ID查询单条数据
     *
     * @param lrid 主键
     * @return 实例对象
     */
    Likerecord queryById(Integer lrid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Likerecord> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param likerecord 实例对象
     * @return 实例对象
     */
    Likerecord insert(Likerecord likerecord);

    /**
     * 修改数据
     *
     * @param likerecord 实例对象
     * @return 实例对象
     */
    Likerecord update(Likerecord likerecord);

    /**
     * 通过主键删除数据
     *
     * @param lrid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer lrid);


    /**
     * 通过空间id去请求该动态的点赞和评论
     * @param said
     * @return
     */
    List<Likerecord> queryBySaid(Integer said);

    /**
     * 用户对好友的空间进行评论
     * @param said
     * @param userid
     * @param record
     * @return
     */
    String publishRecord(Integer said,Integer userid,String record);


    /**
     * 用户对其他发的空间点赞
     * @param said
     * @param userid
     * @return
     */
    Integer publishLike(Integer said,Integer userid);

}