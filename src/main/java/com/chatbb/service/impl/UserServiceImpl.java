package com.chatbb.service.impl;

import com.chatbb.Utils.MD5;
import com.chatbb.dao.MassagesDao;
import com.chatbb.entity.Friendrequest;
import com.chatbb.entity.Massages;
import com.chatbb.entity.User;
import com.chatbb.dao.UserDao;
import com.chatbb.service.SendEmail;
import com.chatbb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-03-25 23:19:17
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Autowired
    private SendEmail sendEmail;

    @Resource
    private MassagesDao massagesDao;


    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer uid) {
        return this.userDao.queryById(uid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getUid());
    }

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer uid) {
        return this.userDao.deleteById(uid) > 0;
    }

    /**
     * 通过昵称检查是否注册
     * @param unickname
     * @return
     */
    @Override
    public Boolean queryByUnickname(String unickname) {
        return this.userDao.queryByunickname(unickname);
    }

    @Override
    public Boolean queryByEmail(String email) {
        return this.userDao.queryByEmail(email);
    }


    /**
     * 登录,顺带生成token，放入数据库并返回前台
     * @param user
     * @return
     */
    @Override
    public String queryByUidAndUpw(User user) {
        if(this.userDao.queryByUidAndUpw(user)){
            //验证正确放入token并返回
            String token=MD5.getMD5(user.getUid().toString());
            user.setToken(token);
            userDao.updataToken(user);
            return token;
        }else{
            return "用户名或密码错误";
        }
    }

    /**
     * token验证登录，返回uid
     *
     * @param token
     * @return
     */
    @Override
    public User checkToken(String token) {
        User user=userDao.checkToken(token);
//        if(user==null){
//            return "没有token";
//        }else{
//            return uid.toString();
//        }
        return user;


    }

    /**
     * 邮箱找回密码，返回Uid，便于后面该密码
     *
     * @param uemail
     * @return
     */
    @Override
    public String queryByEmailObject(String uemail) {
        Integer uid=userDao.queryByEmailObject(uemail);
        if(uid==null){
            return "该邮箱暂未注册";
        }else{
            //注册了就发送验证码，更新验证码
            String emailCode=sendEmail.send(uemail);
            //emailCode存入数据库
            User user=new User();
            user.setUemailcode(emailCode);
            user.setUid(uid);
            userDao.update(user);
            return uid.toString();
        }
    }

    /**
     * 通过邮箱验证码来重置验证码
     *
     * @param user
     * @return
     */
    @Override
    public Integer changepassword(User user) {
        return userDao.changepassword(user);
    }

    /**
     * 通过昵称或id模糊搜索用户，
     *
     * @param uidoruname
     * @return
     */
    @Override
    public List<User> searchUserByidname(String uidoruname) {
        return userDao.searchUserByidname(uidoruname);
    }

    /**
     * 根据uidlist来批量查询user
     *
     * @param friendrequest
     * @return
     */
    @Override
    public List<User> queryByUidList(List<Friendrequest> friendrequest) {
        return userDao.queryByUidList(friendrequest);
    }

    /**
     * 尝试一下两个表连接查询，和上面的批量查询做对比
     * 这个地方要多加一个查询，把每个好友的最后一条消息放在里面
     *
     * @param uid
     * @return
     */
    @Override
    public List<User> queryFriendList(Integer uid) {
        List<User> userlist=userDao.queryFriendList(uid);
        Massages massages=null;
        for(int i=0;i<userlist.size();i++){

            massages=massagesDao.queryLastMessage(uid,userlist.get(i).getUid());
            if(massages!=null){
                userlist.get(i).setLastMessage(massages.getMassage());
                userlist.get(i).setLastMessageDate(massages.getMassagedate().toString());
            }
            massages=null;
        }
        return userlist;
    }

    /**
     * 改变用户是否在线的状态
     *
     * @param uid
     * @param ustate
     */
    @Override
    public void updataStateById(Integer uid, String ustate) {
        this.userDao.updataStateById(uid,ustate);
    }

    /**
     * 用户修改个性签名
     *
     * @param uid
     * @param uPersonalizedsignature
     */
    @Override
    public void updateSignature(Integer uid, String uPersonalizedsignature) {
        this.userDao.updateSignature(uid,uPersonalizedsignature);
    }

    /**
     * 更新用户头像地址
     *
     * @param uid
     * @param avatar
     */
    @Override
    public void updateAvatarById(Integer uid, String avatar) {
        this.userDao.updateAvatarById(uid,avatar);
    }
}