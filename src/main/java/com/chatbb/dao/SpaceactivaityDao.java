package com.chatbb.dao;

import com.chatbb.entity.Spaceactivaity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Spaceactivaity)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-19 16:09:15
 */
@Mapper
public interface SpaceactivaityDao {

    /**
     * 通过ID查询单条数据
     *
     * @param said 主键
     * @return 实例对象
     */
    Spaceactivaity queryById(Integer said);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Spaceactivaity> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    //查询某个用户的空间动态
    List<Spaceactivaity> queryUserSpaceActivityByUid(Integer userid);

    //查询改好友的空间动态
    List<Spaceactivaity> queryFriendsSpaceActivityByUid(Integer userid);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param spaceactivaity 实例对象
     * @return 对象列表
     */
    List<Spaceactivaity> queryAll(Spaceactivaity spaceactivaity);

    /**
     * 新增数据
     *
     * @param spaceactivaity 实例对象
     * @return 影响行数
     */
    int insert(Spaceactivaity spaceactivaity);

    /**
     * 修改数据
     *
     * @param spaceactivaity 实例对象
     * @return 影响行数
     */
    int update(Spaceactivaity spaceactivaity);

    /**
     * 通过主键删除数据
     *
     * @param said 主键
     * @return 影响行数
     */
    int deleteById(Integer said);

}