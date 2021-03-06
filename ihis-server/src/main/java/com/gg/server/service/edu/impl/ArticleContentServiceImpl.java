package com.gg.server.service.edu.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gg.server.config.Exception.ErrorCodeException;
import com.gg.server.entity.Doctor;
import com.gg.server.entity.Role;
import com.gg.server.entity.edu.*;
import com.gg.server.mapper.DoctorMapper;
import com.gg.server.mapper.edu.*;
import com.gg.server.pojo.RespBean;
import com.gg.server.pojo.RespPageBean;
import com.gg.server.pojo.enums.AccountType;
import com.gg.server.pojo.enums.ArticleStatus;
import com.gg.server.pojo.enums.ArticleType;
import com.gg.server.pojo.param.edu.ArticleContentParam;
import com.gg.server.pojo.param.edu.CoverParam;
import com.gg.server.service.edu.ArticleContentService;
import com.gg.server.utils.DoctorUtils;
import com.gg.server.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 资讯文章内容表 服务实现类
 * </p>
 *
 * @author gg
 * @since 2021-05-06
 */
@Service
public class ArticleContentServiceImpl extends ServiceImpl<ArticleContentMapper, ArticleContent> implements ArticleContentService {

    @Autowired
    private ArticleContentMapper articleContentMapper;
    @Autowired
    private ArticleDetailMapper articleDetailMapper;
    @Autowired
    private RefundInfoMapper refundInfoMapper;
    @Autowired
    private DoctorBaseMapper doctorBaseMapper;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private CommentInfoMapper commentInfoMapper;


    /**
     * 添加文章
     * @param articleContentParam
     * @return
     */
    @Override
    @Transactional
    public RespBean addArticle(ArticleContentParam articleContentParam,Boolean draft) {
        // 内容，可替换为非关系数据库保存
        ArticleDetail articleDetail = new ArticleDetail();
        int rndInt = RandomUtil.rndInt(0, 100000);
        articleDetail.setId(rndInt);
        articleDetail.setArticleDetail(articleContentParam.getContent());
        articleDetailMapper.insert(articleDetail);
        // 其他信息
        ArticleContent articleContent = new ArticleContent();
        articleContent.setCid(articleContentParam.getChannelId());
        if (articleContentParam.getDoctorId()!= null){
            // 代发
            articleContent.setCreatorId(articleContentParam.getDoctorId());
            DoctorBase doctorBase = doctorBaseMapper.selectById(articleContentParam.getDoctorId());
            articleContent.setCreatorName(doctorBase.getName());
            articleContent.setHspId(doctorBase.getHspId());
        }else{
            // 己发
            articleContent.setCreatorId(DoctorUtils.getCurrentAdmin().getId());
            Doctor doctor = doctorMapper.selectById(DoctorUtils.getCurrentAdmin().getId());
            DoctorBase doctorBase = doctorBaseMapper.selectById(doctor.getBaseId());
            articleContent.setHspId(doctorBase.getHspId());
            articleContent.setCreatorName(DoctorUtils.getCurrentAdmin().getRealName());
        }
        articleContent.setCreatorTime(LocalDateTime.now());
        articleContent.setUpdateTime(LocalDateTime.now());
        articleContent.setTitle(articleContentParam.getTitle());
        articleContent.setContentSummary(articleContentParam.getContent());
        articleContent.setContentId(String.valueOf(rndInt));
        articleContent.setArticleType(articleContentParam.getArticleType());
        CoverParam cover = articleContentParam.getCover();
        articleContent.setCoverCount(cover.getType());
        if (cover.getType() == 1){
            if(cover.getImages().size() == 0){

            }
            articleContent.setCoverUrl(cover.getImages().get(0));
        }
        if (draft) {
            articleContent.setArticleState(ArticleStatus.DRAFT);
        }else {
            articleContent.setArticleState(ArticleStatus.WAIT_REVIEW);
        }
        if (articleContentParam.getArticleType().equals(ArticleType.VOD)){
            articleContent.setContentUrl(articleContentParam.getContentUrl());
        }
        articleContentMapper.insert(articleContent);
        return RespBean.success("新增成功");
    }

    @Override
    public RespBean updateArticle(ArticleContentParam articleContentParam, Integer id, Boolean draft) {
        // 详细内容
        ArticleContent articleContent = articleContentMapper.selectById(id);
        ArticleDetail articleDetail = new ArticleDetail();
        articleDetail.setId(articleContent.getId());
        articleDetail.setArticleDetail(articleContentParam.getContent());
        articleDetailMapper.updateById(articleDetail);
        // 其他信息
        articleContent.setCid(articleContentParam.getChannelId());
        articleContent.setTitle(articleContentParam.getTitle());
        articleContent.setContentSummary(articleContentParam.getContent());
        articleContent.setUpdateTime(LocalDateTime.now());
        articleContent.setContentUrl(articleContentParam.getContentUrl());
        CoverParam cover = articleContentParam.getCover();
        articleContent.setCoverCount(cover.getType()); // 为0
        if (cover.getType() == 1){ // 为1
            if(cover.getImages().size() == 0){ // 无文件改为0
                articleContent.setCoverCount(0);
                articleContent.setCoverUrl(null);
            }else{ // 有文件保存为1
                articleContent.setCoverCount(cover.getType());
                articleContent.setCoverUrl(cover.getImages().get(0));
            }
        }
        if (draft) {
            articleContent.setArticleState(ArticleStatus.DRAFT);
        }else {
            articleContent.setArticleState(ArticleStatus.WAIT_REVIEW);
        }
        articleContentMapper.updateById(articleContent);
        return RespBean.success("修改成功");

    }

    /**
     * 查询文章
     * @param currentPage
     * @param size
     * @param articleContent
     * @param dateScope
     * @return
     */
    @Override
    public RespPageBean getArticles(Integer currentPage, Integer size, ArticleContent articleContent, String[] dateScope) {
        if (DoctorUtils.getCurrentAdmin().getAccountType().equals(AccountType.DOCTOR)){
            articleContent.setCreatorId(DoctorUtils.getCurrentAdmin().getId());
        }
        if (DoctorUtils.getCurrentAdmin().getAccountType().equals(AccountType.HOSPITAL)){
            List<Role> roles = DoctorUtils.getCurrentAdmin().getRoles();
            for (Role role:roles){
                if(role.getHspId() !=null){
                    articleContent.setHspId(role.getHspId());
                    break;
                }
            }
        }
        Page<ArticleContent> page = new Page<>(currentPage,size);
        IPage<ArticleContent> articleByPage = null;
        if (ArticleStatus.FINISH.equals(articleContent.getArticleState())){
            articleByPage = articleContentMapper.getArticleByPage(page, articleContent,1, dateScope);
        }else{
            articleByPage = articleContentMapper.getArticleByPage(page, articleContent,0, dateScope);
        }

        List<ArticleContent> records = articleByPage.getRecords();
        for (ArticleContent record : records){
            Integer selectCount = commentInfoMapper.selectCount(new QueryWrapper<CommentInfo>()
                    .eq("zid", record.getId()));
            record.setCommentCount(selectCount);
            record.setLikeCount(selectCount+3);
            record.setCommentCount(selectCount == 0 ? 0:selectCount-1);
        }

        return new RespPageBean(articleByPage.getTotal(),articleByPage.getRecords());
    }

    @Override
    public ArticleContent getArticle(Integer id){
        ArticleContent articleContent = articleContentMapper.selectById(id);
        if (articleContent == null) {
            throw ErrorCodeException.valueOf("文章已删除");
        }
        return articleContent;
    }

    @Override
    public List<ArticleContent> getUncheckArticles(ArticleStatus articleStatus) {

        List<ArticleContent> articleByStatus = articleContentMapper.getArticleByStatus(articleStatus, 1);
        return articleByStatus;
    }

    @Override
    @Transactional
    public RespBean articleRefund(RefundInfo refundInfo) {
        if (refundInfo.getCheckId() == null){
            throw ErrorCodeException.valueOf("参数异常");
        }
        ArticleContent content = articleContentMapper.selectById(refundInfo.getCheckId());
        content.setArticleState(ArticleStatus.FAIL);
        content.setFirstCheck(false);
        content.setUpdateTime(LocalDateTime.now());
        articleContentMapper.updateById(content);
        // 插入驳回记录
        refundInfo.setRefundType(0);// 0为资讯1为人
        refundInfo.setCheckAccountId(DoctorUtils.getCurrentAdmin().getId());
        refundInfo.setCheckAccountName(DoctorUtils.getCurrentAdmin().getRealName());
        refundInfoMapper.insert(refundInfo);
        return RespBean.success("驳回成功");
    }

}
