package com.gg.server.controller.edu;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gg.server.entity.edu.CommentInfo;
import com.gg.server.mapper.edu.CommentInfoMapper;
import com.gg.server.pojo.RespBean;
import com.gg.server.pojo.param.edu.ArticleContentParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 作品评论信息表 前端控制器
 * </p>
 *
 * @author gg
 * @since 2021-05-06
 */
@RestController
@RequestMapping("/comment")
public class CommentInfoController {

    @Autowired
    private CommentInfoMapper commentInfoMapper;

    @ApiOperation(value = "获得资讯")
    @GetMapping("/getComments/{id}")
    public List<CommentInfo> getComments(@PathVariable Integer id){

        List<CommentInfo> commentInfos = commentInfoMapper.selectList(new QueryWrapper<CommentInfo>().eq("zid", id));

        return commentInfos;
    }




}

