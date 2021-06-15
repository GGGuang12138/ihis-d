package com.gg.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gg.server.entity.FileInfo;
import com.gg.server.pojo.RespPageBean;
import org.springframework.stereotype.Service;

@Service
public interface FileInfoService extends IService<FileInfo> {

    RespPageBean getFiles(Integer currentPage,Integer size,FileInfo fileInfo,Integer[] sizeScope);

}
