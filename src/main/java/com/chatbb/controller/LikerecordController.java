package com.chatbb.controller;

import com.chatbb.entity.Likerecord;
import com.chatbb.service.LikerecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Likerecord)表控制层
 *
 * @author makejava
 * @since 2022-04-20 10:56:33
 */
@RestController
@RequestMapping("likerecord")
public class LikerecordController {
    /**
     * 服务对象
     */
    @Resource
    private LikerecordService likerecordService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Likerecord selectOne(Integer id) {
        return this.likerecordService.queryById(id);
    }


    /**
     * 接收好友的评论，需要的数据有，那一条空间，评论人id，评论内容
     * @return
     */
    @GetMapping("publishrecord")
    public String publishRecord(Integer said,Integer userid,String record){
        return this.likerecordService.publishRecord(said,userid,record);
    }

    @GetMapping("publishLike")
    public Integer publishLike(Integer said,Integer userid){
        return this.likerecordService.publishLike(said,userid);
    }

}