package com.gg.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gg.server.entity.FileInfo;
import com.gg.server.entity.edu.ArticleContent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gg
 * @since 2021-06-09
 */
@Component
public interface FileInfoMapper extends BaseMapper<FileInfo> {

    /**
     *
     * @param page
     * @param fileInfo
     * @param sizeScope
     * @return
     */
    IPage<FileInfo> getArticleByPage(Page<FileInfo> page,
                                           @Param("fileInfo") FileInfo fileInfo,
                                           @Param("sizeScope") Integer[] sizeScope);

}
