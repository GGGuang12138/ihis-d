package com.gg.server.controller.edu;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gg.server.entity.edu.DoctorBase;
import com.gg.server.entity.edu.RefundInfo;
import com.gg.server.mapper.edu.RefundInfoMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 审核驳回信息表（包括人员驳回、资讯发布驳回） 前端控制器
 * </p>
 *
 * @author gg
 * @since 2021-06-12
 */
@RestController
@RequestMapping("")
public class RefundInfoController {

    @Autowired
    private RefundInfoMapper refundInfoMapper;

    @ApiOperation(value = "获取驳回信息")
    @GetMapping("/user/getRufundInfo/{id}")
    public RefundInfo getRefundInfo(@PathVariable Integer id){
        List<RefundInfo> refundInfos = refundInfoMapper.selectList(new QueryWrapper<RefundInfo>()
                .eq("checkId", id)
                .eq("refundType", 1).orderByDesc("id"));
        RefundInfo refundInfo = refundInfos.get(0);
        return refundInfo;
    }

    @ApiOperation(value = "获取文章驳回信息")
    @GetMapping("/edu/manager/getArticleRefundInfo/{id}")
    public RefundInfo getArticleRefundInfo(@PathVariable Integer id){
        List<RefundInfo> refundInfos = refundInfoMapper.selectList(new QueryWrapper<RefundInfo>()
                .eq("checkId", id)
                .eq("refundType", 0).orderByDesc("id"));
        RefundInfo refundInfo = refundInfos.get(0);
        return refundInfo;
    }



}

