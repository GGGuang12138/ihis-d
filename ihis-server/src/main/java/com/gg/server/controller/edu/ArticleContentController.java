package com.gg.server.controller.edu;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gg.server.config.Exception.ErrorCodeException;
import com.gg.server.entity.Role;
import com.gg.server.entity.edu.ArticleContent;
import com.gg.server.entity.edu.DoctorBase;
import com.gg.server.entity.edu.RefundInfo;
import com.gg.server.mapper.edu.ArticleContentMapper;
import com.gg.server.mapper.edu.DoctorBaseMapper;
import com.gg.server.pojo.RespBean;
import com.gg.server.pojo.RespPageBean;
import com.gg.server.pojo.enums.AccountType;
import com.gg.server.pojo.enums.ArticleStatus;
import com.gg.server.pojo.param.edu.ArticleContentParam;
import com.gg.server.service.edu.ArticleContentService;
import com.gg.server.utils.DoctorUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 资讯文章内容表 前端控制器
 * </p>
 *
 * @author gg
 * @since 2021-05-06
 */
@RestController
@RequestMapping("/edu")
public class ArticleContentController {

    @Autowired
    private ArticleContentService articleContentService;

    @Autowired
    private ArticleContentMapper articleContentMapper;

    @Autowired
    private DoctorBaseMapper doctorBaseMapper;

    @ApiOperation(value = "新增资讯")
    @PostMapping("/release/addArticle")
    public RespBean addArticle(@RequestParam Boolean draft,
                               @RequestBody ArticleContentParam articleContentParam){
        if (draft == null || articleContentParam == null){
            return RespBean.error("参数异常");
        }
        articleContentService.addArticle(articleContentParam, draft);
        return RespBean.error("新增失败");
    }

    @ApiOperation(value = "修改资讯")
    @PostMapping("/release/updateArticle/{id}")
    public RespBean updateArticle(@RequestParam Boolean draft,
                                  @PathVariable Integer id,
                                  @RequestBody ArticleContentParam articleContentParam){
        if (draft == null || articleContentParam == null){
            return RespBean.error("参数异常");
        }
        articleContentService.updateArticle(articleContentParam, id, draft);
        return RespBean.error("修改失败");
    }


    @ApiOperation(value = "获取所有文章")
    @PostMapping("/manager/getArticles")
    public RespPageBean getArticles(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    @RequestBody ArticleContent articleContent,
                                    String beginTime,
                                    String endTime
    ){
        return articleContentService.getArticles(currentPage,size,articleContent, beginTime == null? null :new String[]{beginTime+" 00:00:00",endTime == null?null: endTime+" 23:59:59"});
    }

    @ApiOperation(value = "获取所有未审核文章")
    @GetMapping("/manager/getUnCheckArticles")
    public List<ArticleContent> getUncheckArticles(@RequestParam ArticleStatus articleStatus
    ){
        if (DoctorUtils.getCurrentAdmin().getAccountType() == AccountType.HOSPITAL){
            List<Role> roles = DoctorUtils.getCurrentAdmin().getRoles();
            for (Role role:roles){
                if (role.getHspId() != null){
                   return articleContentMapper.getArticleByStatusHsp(articleStatus, 0, role.getHspId());
                }
            }
        }
        return articleContentService.getUncheckArticles(articleStatus);
    }

    @ApiOperation(value = "获取文章内容")
    @GetMapping("/release/getArticle/{id}")
    public ArticleContent getArticle(@PathVariable Integer id){
        return articleContentService.getArticle(id);
    }

    @ApiOperation(value = "文章驳回")
    @PostMapping("/manager/failArticle")
    public RespBean failArticle(@RequestBody RefundInfo refundInfo){
        return articleContentService.articleRefund(refundInfo);
    }

    @ApiOperation(value = "取消审核")
    @PostMapping("/manager/cancelCheck")
    public RespBean cancelCheck(@RequestBody String target){
        Integer articleId;
        try {
            JSONObject object = JSON.parseObject(target);
            articleId = object.getInteger("target");
        } catch (Exception e) {
            throw ErrorCodeException.valueOf("错误的文章ID");
        }
        ArticleContent articleContent = articleContentMapper.selectById(articleId);
        articleContent.setArticleState(ArticleStatus.DRAFT);
        articleContent.setUpdateTime(LocalDateTime.now());
        int i = articleContentMapper.updateById(articleContent);
        if (i == 1){
            return RespBean.success("成功");
        }
        return RespBean.error("失败");
    }

    @ApiOperation(value = "删除文章")
    @PostMapping("/manager/deleteArticle")
    public RespBean deleteArticle(@RequestBody String target){
        Integer articleId;
        try {
            JSONObject object = JSON.parseObject(target);
            articleId = object.getInteger("target");
        } catch (Exception e) {
            throw ErrorCodeException.valueOf("错误的文章ID");
        }
        int i = articleContentMapper.deleteById(articleId);
        if (i == 1){
            return RespBean.success("成功");
        }
        return RespBean.error("失败");
    }

    @ApiOperation(value = "下架")
    @PostMapping("/manager/offArticle")
    public RespBean offArticle(@RequestBody String target){
        Integer articleId;
        try {
            JSONObject object = JSON.parseObject(target);
            articleId = object.getInteger("target");
        } catch (Exception e) {
            throw ErrorCodeException.valueOf("错误的文章ID");
        }
        ArticleContent articleContent = articleContentMapper.selectById(articleId);
        articleContent.setArticleState(ArticleStatus.OFF);
        articleContent.setUpdateTime(LocalDateTime.now());
        int i = articleContentMapper.updateById(articleContent);
        if (i == 1){
            return RespBean.success("成功");
        }
        return RespBean.error("失败");
    }

    @ApiOperation(value = "通过审核")
    @PostMapping("/manager/passCheck")
    public RespBean passCheck(@RequestBody String target){
        Integer articleId;
        try {
            JSONObject object = JSON.parseObject(target);
            articleId = object.getInteger("target");
        } catch (Exception e) {
            throw ErrorCodeException.valueOf("错误的文章ID");
        }
        ArticleContent articleContent = articleContentMapper.selectById(articleId);
        if (DoctorUtils.getCurrentAdmin().getAccountType().equals(AccountType.HOSPITAL)){
            articleContent.setFirstCheck(true); //false为初审过
            articleContent.setUpdateTime(LocalDateTime.now());
            articleContentMapper.updateById(articleContent);
            return RespBean.success("一审核通过");
        }else{
            articleContent.setArticleState(ArticleStatus.PASS);
            articleContent.setFirstCheck(true);
            articleContent.setUpdateTime(LocalDateTime.now());
            articleContentMapper.updateById(articleContent);
            return RespBean.success("二审通过");
        }
    }

}

