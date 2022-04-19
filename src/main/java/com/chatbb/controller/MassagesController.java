package com.chatbb.controller;

import com.chatbb.entity.Massages;
import com.chatbb.service.MassagesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Massages)表控制层
 *
 * @author makejava
 * @since 2022-04-02 21:33:03
 */
@RestController
@RequestMapping("massages")
public class MassagesController {
    /**
     * 服务对象
     */
    @Resource
    private MassagesService massagesService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Massages selectOne(Integer id) {
        return this.massagesService.queryById(id);
    }

    @GetMapping("requestMassage")
    public List<Massages> requestMassage(Integer userid,Integer friendid){
        return massagesService.requestMassage(userid,friendid);
    }

}