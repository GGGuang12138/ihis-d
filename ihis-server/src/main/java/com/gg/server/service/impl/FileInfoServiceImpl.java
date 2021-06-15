package com.gg.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gg.server.entity.FileInfo;
import com.gg.server.entity.edu.ArticleContent;
import com.gg.server.mapper.FileInfoMapper;
import com.gg.server.pojo.RespPageBean;
import com.gg.server.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gg
 * @since 2021-06-09
 */
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Override
    public RespPageBean getFiles(Integer currentPage, Integer size, FileInfo fileInfo, Integer[] sizeScope) {
        Page<FileInfo> page = new Page<>(currentPage,size);
        IPage<FileInfo> articleByPage = fileInfoMapper.getArticleByPage(page, fileInfo, sizeScope);
        List<FileInfo> records = articleByPage.getRecords();
        DecimalFormat d0 = new DecimalFormat("#.#");
        DecimalFormat d1 = new DecimalFormat("#.#");
        for (FileInfo record : records){
            String fileSize = record.getFileSize();
            String s = null;
            double i = Integer.parseInt(fileSize) * Math.pow(10,-6);
            if (i < 1){
                double i1 = Integer.parseInt(fileSize) * Math.pow(10, -3);
                String s1 = d1.format(i1) + 'K';
                record.setFileSize(s1);
            }else {
                record.setFileSize(d1.format(i)+'M');
            }
        }
        return new RespPageBean(articleByPage.getTotal(),articleByPage.getRecords());
    }
}
