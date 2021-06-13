package com.gg.server.service.edu;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gg.server.entity.edu.ArticleContent;
import com.gg.server.entity.edu.RefundInfo;
import com.gg.server.pojo.RespBean;
import com.gg.server.pojo.RespPageBean;
import com.gg.server.pojo.enums.ArticleStatus;
import com.gg.server.pojo.param.edu.ArticleContentParam;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 资讯文章内容表 服务类
 * </p>
 *
 * @author gg
 * @since 2021-05-06
 */
@Service
public interface ArticleContentService extends IService<ArticleContent> {

    /**
     * 添加文章
     * @param articleContentParam
     * @return
     */
    RespBean addArticle(ArticleContentParam articleContentParam,Boolean draft);

    /**
     * 修改文章
     * @param articleContentParam
     * @param id
     * @param draft
     * @return
     */
    RespBean updateArticle(ArticleContentParam articleContentParam,Integer id, Boolean draft);

    /**
     * 查询文章
     * @param currentPage
     * @param size
     * @param articleContent
     * @param dateScope
     * @return
     */
    RespPageBean getArticles(Integer currentPage, Integer size, ArticleContent articleContent, String[] dateScope);

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    ArticleContent getArticle(Integer id);


    /**
     * 获取待审核资讯
     * @return
     */
    List<ArticleContent> getUncheckArticles(ArticleStatus articleStatus);


    /**
     * 文章驳回
     * @param refundInfo
     * @return
     */
    RespBean articleRefund(RefundInfo refundInfo);
}
