package com.gg.server.controller.edu;


import com.gg.server.entity.edu.ArticleContent;
import com.gg.server.entity.edu.RefundInfo;
import com.gg.server.pojo.RespBean;
import com.gg.server.pojo.RespPageBean;
import com.gg.server.pojo.enums.ArticleStatus;
import com.gg.server.pojo.param.edu.ArticleContentParam;
import com.gg.server.service.edu.ArticleContentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @GetMapping("/manager/getArticles")
    public RespPageBean getArticles(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    ArticleContent articleContent,
                                    String beginTime,
                                    String endTime
    ){
        return articleContentService.getArticles(currentPage,size,articleContent, beginTime == null? null :new String[]{beginTime+" 00:00:00",endTime == null?null: endTime+" 23:59:59"});
    }

    @ApiOperation(value = "获取所有未审核文章")
    @GetMapping("/manager/getUnCheckArticles")
    public List<ArticleContent> getUncheckArticles(@RequestParam ArticleStatus articleStatus
    ){
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


}

