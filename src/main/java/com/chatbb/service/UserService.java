package com.chatbb.service;

import com.chatbb.entity.Friendrequest;
import com.chatbb.entity.User;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2022-03-25 23:19:16
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    User queryById(Integer uid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer uid);


    /**
     * 通过昵称来查询是否注册
     * @param unickname
     * @return
     */
    Boolean queryByUnickname(String unickname);


    /**
     * 查询邮箱是否被注册
     * @param email
     * @return
     */
    Boolean queryByEmail(String email);


    /**
     * 登录
     * @param user
     * @return
     */
    String queryByUidAndUpw(User user);


    /**
     * token验证登录，返回uid
     * @param token
     * @return
     */
    User checkToken(String  token);

    /**
     * 邮箱找回密码，返回Uid，便于后面该密码
     * @param uemail
     * @return
     */
    String queryByEmailObject(String uemail);

    /**
     * 通过邮箱验证码来重置验证码
     * @param user
     * @return
     */
    Integer changepassword(User user);


    /**
     * 通过昵称或id模糊搜索用户，
     * @param uidoruname
     * @return
     */
    List<User> searchUserByidname(String uidoruname);


    /**
     * 根据uidlist来批量查询user
     * @param friendrequest
     * @return
     */
    List<User> queryByUidList(List<Friendrequest> friendrequest);


    /**
     * 尝试一下两个表连接查询，和上面的批量查询做对比
     * @param uid
     * @return
     */
    List<User> queryFriendList(Integer uid);

    /**
     * 改变用户是否在线的状态
     * @param uid
     * @param ustate
     */
    void updataStateById(Integer uid,String ustate);

    /**
     * 用户修改个性签名
     * @param uid
     * @param uPersonalizedsignature
     */
    void updateSignature(Integer uid,String uPersonalizedsignature);

    /**
     * 更新用户头像地址
     * @param uid
     * @param avatar
     */
    void updateAvatarById(Integer uid,String avatar);
}