package com.chatbb.controller;

import com.chatbb.entity.Spaceactivaity;
import com.chatbb.service.SpaceactivaityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Spaceactivaity)表控制层
 *
 * @author makejava
 * @since 2022-04-19 16:09:15
 */
@RestController
@RequestMapping("spaceactivaity")
public class SpaceactivaityController {
    /**
     * 服务对象
     */
    @Resource
    private SpaceactivaityService spaceactivaityService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Spaceactivaity selectOne(Integer id) {
        return this.spaceactivaityService.queryById(id);
    }

}