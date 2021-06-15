package com.gg.server.mapper.edu;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gg.server.entity.edu.ArticleContent;
import com.gg.server.pojo.enums.ArticleStatus;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 资讯文章内容表 Mapper 接口
 * </p>
 *
 * @author gg
 * @since 2021-05-07
 */
@Component
public interface ArticleContentMapper extends BaseMapper<ArticleContent> {


    /**
     * 查询文章
     * @param page
     * @param articleContent
     * @param createDateScope
     * @return
     */
    IPage<ArticleContent> getArticleByPage(Page<ArticleContent> page,
                                           @Param("articleContent") ArticleContent articleContent,
                                           @Param("isFinish") Integer isFinish,
                                           @Param("createDateScope") String[] createDateScope
    );


    List<ArticleContent> getArticleByStatus(@Param("articleStatus")ArticleStatus articleStatus,
                                            @Param("firstCheck") Integer firstCheck);


    /**
     * 医院版本
     * @param articleStatus
     * @param firstCheck
     * @param hspId
     * @return
     */
    List<ArticleContent> getArticleByStatusHsp(@Param("articleStatus")ArticleStatus articleStatus,
                                            @Param("firstCheck") Integer firstCheck,
                                            @Param("hspId") Integer hspId);




}
