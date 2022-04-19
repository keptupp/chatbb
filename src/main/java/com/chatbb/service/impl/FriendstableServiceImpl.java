package com.chatbb.service.impl;

import com.chatbb.dao.FriendrequestDao;
import com.chatbb.entity.Friendstable;
import com.chatbb.dao.FriendstableDao;
import com.chatbb.service.FriendstableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (Friendstable)表服务实现类
 *
 * @author makejava
 * @since 2022-03-31 23:11:26
 */
@Service("friendstableService")
public class FriendstableServiceImpl implements FriendstableService {
    @Resource
    private FriendstableDao friendstableDao;

    @Resource
    private FriendrequestDao friendrequestDao;


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Friendstable> queryAllByLimit(int offset, int limit) {
        return this.friendstableDao.queryAllByLimit(offset, limit);
    }

    /**

     * 《《《《《《这个地方很重要》》》》》
     * 这个地方现在不重要了，已经在请求表里面做了判断
     * 凡是在请求表里面的，就一定互相不为好友
     *
     * @param
     * @return 实例对象
     */
    @Override
    public void insert(String myid,String friendid) {
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String nowDate=dateFormat.format(date);

        //先创建出来一个实例
        Friendstable friendstable1=new Friendstable();
        friendstable1.setUserid(Integer.valueOf(myid));
        friendstable1.setFriendid(Integer.valueOf(friendid));
        friendstable1.setAdddate(nowDate);

        //将该好友添加进用户的好友列表里面
        friendstableDao.insert(friendstable1);


        //将用户也添加到好友的好友列表里面
        friendstable1.setUserid(Integer.valueOf(friendid));
        friendstable1.setFriendid(Integer.valueOf(myid));
        friendstableDao.insert(friendstable1);

        //删除一下二者互加的记录
        friendrequestDao.deleteRequest(Integer.valueOf(myid),Integer.valueOf(friendid));
        friendrequestDao.deleteRequest(Integer.valueOf(friendid),Integer.valueOf(myid));
    }

    /**
     * 根据用户id返回其好友id，用于向该用户的好友推送他是否在线
     *
     * @param id
     * @return
     */
    @Override
    public List<Integer> queryFidByuid(String id) {
        List<Integer> listid=this.friendstableDao.queryFidByuid(Integer.valueOf(id));
        return listid;
    }

    /**
     * 更新好友之间未读消息的条数
     *
     * @param userid
     * @param friendid
     * @param unreadnum
     */
    @Override
    public void updateUnReadNum(Integer userid, Integer friendid, Integer unreadnum) {
        this.friendstableDao.updateUnReadNum(userid,friendid,unreadnum);
    }

    /**
     * 删除好友，单向删除
     *
     * @param userid
     * @param friendid
     */
    @Override
    public void deleteFriend(Integer userid, Integer friendid) {
        this.friendstableDao.deleteFriend(userid,friendid);
    }


}