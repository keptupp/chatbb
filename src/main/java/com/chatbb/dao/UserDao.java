package com.chatbb.dao;

import com.chatbb.entity.Friendrequest;
import com.chatbb.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-25 23:19:15
 */
@Mapper
public interface UserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    User queryById(Integer uid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> queryAll(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 影响行数
     */
    int deleteById(Integer uid);


    /**
     * 通过昵称来检查是否注册
     * @param unickname
     * @return
     */
    boolean queryByunickname(String unickname);

    /**
     * 检查邮箱是否已经被绑定
     * @param email
     * @return
     */
    Boolean queryByEmail(String email);


    /**
     * 登录
     * @param user
     * @return
     */
    Boolean queryByUidAndUpw(User user);

    /**
     * 更新token
     * @param user
     */
    void updataToken(User user);


    /**
     * token登录的时候检查是否有token，并返回uid
     * @param token
     * @return
     */
    User checkToken(String token);


    /**
     * 邮箱改密码，返回该邮箱的id
     * @param uemail
     * @return
     */
    Integer queryByEmailObject(String uemail);

    /**
     * 忘记密码后通过邮箱验证码来重置密码
     * @param user
     * @return
     */
    Integer changepassword(User user);


    /**
     * 模糊查询，根据id或者name
     * @param uidoruname
     * @return
     */
    List<User> searchUserByidname(String uidoruname);

    /**
     * 批量id查询
     * @param uidlist
     * @return
     */
    List<User> queryByUidList(@Param("uidlist") List<Friendrequest> uidlist);


    /**
     * 两表查询>>>>>>>>>>>>>>>>>>>>>>新学的
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