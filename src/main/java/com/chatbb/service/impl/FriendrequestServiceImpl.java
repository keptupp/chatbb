package com.chatbb.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chatbb.dao.FriendstableDao;
import com.chatbb.entity.Friendrequest;
import com.chatbb.dao.FriendrequestDao;
import com.chatbb.entity.Friendstable;
import com.chatbb.service.FriendrequestService;
import com.chatbb.websocket.WebSocket;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Friendrequest)表服务实现类
 *
 * @author makejava
 * @since 2022-03-31 13:13:15
 */
@Service("friendrequestService")
public class FriendrequestServiceImpl implements FriendrequestService {
    @Resource
    private FriendrequestDao friendrequestDao;

    @Resource
    private FriendstableDao friendstableDao;

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    @Override
    public List<Friendrequest> queryById(Integer uid) {
        return this.friendrequestDao.queryById(uid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Friendrequest> queryAllByLimit(int offset, int limit) {
        return this.friendrequestDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     * 目标：判断该用户是否已经加朋友为好友了
     * 是，就返回    是好友
     * 否，就查询该朋友的列表里是否有好友，是就悄咪咪的把他加回来，返回   重新加回
     *                                     不是，就添加一条请求记录 返回  添加成功
     * @param friendrequest 实例对象
     * @return 实例对象
     */
    @Override
    public String insert(Friendrequest friendrequest) {

        //判断该用户是否已经加朋友为好友了;注意，该用户是请求者
        if(friendstableDao.isFriend(friendrequest.getRequestid(),friendrequest.getUid())){
            return "是好友";
        }else{
            //判断该朋友的列表里有没有用户，（这种情况就是用户悄咪咪的把朋友删了
            if(friendstableDao.isFriend(friendrequest.getUid(),friendrequest.getRequestid())){
                Friendstable friendstable = new Friendstable();
                friendstable.setUserid(friendrequest.getRequestid());
                friendstable.setFriendid(friendrequest.getUid());
                friendstable.setAdddate(friendrequest.getRequestdate());
                friendstableDao.insert(friendstable);
                return "重新加回";
            }else{
                //也没有，就说明二者均没有互加，就可以插入一条请求加好友了
                friendrequestDao.insert(friendrequest);

                //获取websocket的session,向对方发出申请信号
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("service","好友申请");
                WebSocket.sendMassageById(friendrequest.getUid().toString(),jsonObject.toJSONString());
                return "添加成功";
            }


        }

    }

    /**
     * 修改数据
     *
     * @param friendrequest 实例对象
     * @return 实例对象
     */

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer uid) {
        return this.friendrequestDao.deleteById(uid) > 0;
    }

    /**
     * 查询是否已经添加为好友了
     * @param friendrequest
     * @return
     */
    @Override
    public boolean queryByUidAndResid(Friendrequest friendrequest) {
        Integer count=friendrequestDao.queryByUidAndResid(friendrequest);
        if(count==0){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 拒绝其他用户的申请
     *
     * @param uid
     * @param requestid
     */
    @Override
    public void refuseAdd(Integer uid, Integer requestid) {
        this.friendrequestDao.deleteRequest(uid,requestid);
    }


}