package com.chatbb.service.impl;

import com.chatbb.entity.Likerecord;
import com.chatbb.dao.LikerecordDao;
import com.chatbb.service.LikerecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Likerecord)表服务实现类
 *
 * @author makejava
 * @since 2022-04-20 10:56:33
 */
@Service("likerecordService")
public class LikerecordServiceImpl implements LikerecordService {
    @Resource
    private LikerecordDao likerecordDao;

    /**
     * 通过ID查询单条数据
     *
     * @param lrid 主键
     * @return 实例对象
     */
    @Override
    public Likerecord queryById(Integer lrid) {
        return this.likerecordDao.queryById(lrid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Likerecord> queryAllByLimit(int offset, int limit) {
        return this.likerecordDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param likerecord 实例对象
     * @return 实例对象
     */
    @Override
    public Likerecord insert(Likerecord likerecord) {
        this.likerecordDao.insert(likerecord);
        return likerecord;
    }

    /**
     * 修改数据
     *
     * @param likerecord 实例对象
     * @return 实例对象
     */
    @Override
    public Likerecord update(Likerecord likerecord) {
        this.likerecordDao.update(likerecord);
        return this.queryById(likerecord.getLrid());
    }

    /**
     * 通过主键删除数据
     *
     * @param lrid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer lrid) {
        return this.likerecordDao.deleteById(lrid) > 0;
    }

    /**
     * 通过空间id去请求该动态的点赞和评论
     *
     * @param said
     * @return
     */
    @Override
    public List<Likerecord> queryBySaid(Integer said) {
        return this.likerecordDao.queryBySaid(said);
    }

    /**
     * 用户对好友的空间进行评论
     *
     * @param said
     * @param userid
     * @param record
     * @return
     */
    @Override
    public String publishRecord(Integer said, Integer userid, String record) {
        //首先检查该用户是否已经有记录了，比如已经评价或者已经点赞了
        if(this.likerecordDao.isRecord(said,userid)>0){
            //说明有记录，选择更新消息
            this.likerecordDao.updateBySaidUserid(said,userid,record);
        }else{
            //没有记录，新增消息
            this.likerecordDao.insertRecord(said,userid,record);
        }
        return "评论成功";
    }

    /**
     * 用户对其他发的空间点赞
     *
     * @param said
     * @param userid
     * @return
     */
    @Override
    public Integer publishLike(Integer said, Integer userid) {
        Likerecord likerecord=this.likerecordDao.isLike(said,userid);
        //返回前台做一个响应,1表示点赞了，加1，0表示取消点赞了，减1
        Integer isLike=0;
        //同样检查是否又记录
        if(likerecord==null){
            //没有记录，直接插入更新点个赞
            this.likerecordDao.insertLike(said,userid,true);
            isLike=1;
        }else{
            //有记录，查看是否点赞
            if(likerecord.getIslike()){
                //点赞了，就去取消点赞
                this.likerecordDao.updateLike(said,userid,false);
                isLike=0;
            }else{
                //没有点赞，去点个赞
                this.likerecordDao.updateLike(said,userid,true);
                isLike=1;
            }

        }
        return isLike;
    }
}