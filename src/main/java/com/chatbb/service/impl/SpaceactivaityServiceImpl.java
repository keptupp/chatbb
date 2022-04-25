package com.chatbb.service.impl;

import com.chatbb.entity.Likerecord;
import com.chatbb.entity.Spaceactivaity;
import com.chatbb.dao.SpaceactivaityDao;
import com.chatbb.service.LikerecordService;
import com.chatbb.service.SpaceactivaityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Spaceactivaity)表服务实现类
 *
 * @author makejava
 * @since 2022-04-19 16:09:15
 */
@Service("spaceactivaityService")
public class SpaceactivaityServiceImpl implements SpaceactivaityService {
    @Resource
    private SpaceactivaityDao spaceactivaityDao;

    @Resource
    private LikerecordService likerecordService;

    /**
     * 通过ID查询单条数据
     *
     * @param said 主键
     * @return 实例对象
     */
    @Override
    public Spaceactivaity queryById(Integer said) {
        return this.spaceactivaityDao.queryById(said);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Spaceactivaity> queryAllByLimit(int offset, int limit) {
        return this.spaceactivaityDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param spaceactivaity 实例对象
     * @return 实例对象
     */
    @Override
    public Spaceactivaity insert(Spaceactivaity spaceactivaity) {
        this.spaceactivaityDao.insert(spaceactivaity);
        return spaceactivaity;
    }

    /**
     * 修改数据
     *
     * @param spaceactivaity 实例对象
     * @return 实例对象
     */
    @Override
    public Spaceactivaity update(Spaceactivaity spaceactivaity) {
        this.spaceactivaityDao.update(spaceactivaity);
        return this.queryById(spaceactivaity.getSaid());
    }

    /**
     * 通过主键删除数据
     *
     * @param said 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer said) {
        return this.spaceactivaityDao.deleteById(said) > 0;
    }

    public List<Spaceactivaity> addRecord(List<Spaceactivaity> spaceactivaities){
        Spaceactivaity spaceactivaity;
        int like=0;
        //然后依次去请求每条空间动态的评论，顺便记录点赞数
        for(int i=0;i<spaceactivaities.size();i++){
            spaceactivaity=spaceactivaities.get(i);
            //请求该空间动态的点赞和评论
            List<Likerecord> likerecords=this.likerecordService.queryBySaid(spaceactivaity.getSaid());
            List<Likerecord> hasRecord=new ArrayList<>();
            for(int j=0;j<likerecords.size();j++){
                if(likerecords.get(j).getIsrecord()){
                    //是有评论的
                    hasRecord.add(likerecords.get(j));
                }
                if(likerecords.get(j).getIslike()){
                    //点过赞的
                    like++;
                }
            }
            spaceactivaities.get(i).setLikeRecords(hasRecord);
            spaceactivaities.get(i).setLike(like);
            like=0;
        }
        return spaceactivaities;
    }

    /**
     * 根据用户id去查询其好友的空间动态
     *
     * @param userid
     * @return
     */
    @Override
    public List<Spaceactivaity> requestFriendsSpaceActivity(Integer userid) {


        //请求出了该用户好友的所有的空间动态
        List<Spaceactivaity> spaceactivaities=this.spaceactivaityDao.queryFriendsSpaceActivityByUid(userid);
        //把原本的那段代码，拆到addRecord里面，便于细分查询的是好友的，还是本用户的
        return this.addRecord(spaceactivaities);
    }

    /**
     * 请求一个用户的全部空间动态
     *
     * @param userid
     * @return
     */
    @Override
    public List<Spaceactivaity> requestOneUserSpaceactivity(Integer userid) {
        //请求出该用户的空间动态
        List<Spaceactivaity> spaceactivaities=this.spaceactivaityDao.queryUserSpaceActivityByUid(userid);
        //然后请求出每一条动态下对应的评论
        return this.addRecord(spaceactivaities);
    }
}