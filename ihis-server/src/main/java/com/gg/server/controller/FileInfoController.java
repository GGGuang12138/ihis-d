package com.gg.server.controller;


import com.gg.server.entity.FileInfo;
import com.gg.server.entity.Role;
import com.gg.server.entity.edu.ArticleContent;
import com.gg.server.pojo.RespPageBean;
import com.gg.server.service.FileInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gg
 * @since 2021-06-09
 */
@RestController
@RequestMapping("/fileInfo")
public class FileInfoController {

    @Autowired
    private FileInfoService fileInfoService;

    @ApiOperation(value = "查询用户菜单列表")
    @GetMapping("/getFileInfo")
    public List<FileInfo> getMenuByDoctorId(){
        return fileInfoService.list();
    }

    @ApiOperation(value = "获取所有文章")
    @PostMapping("/manager/getFile")
    public RespPageBean getFiles(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    @RequestBody FileInfo fileInfo,
                                    Integer beginSize,
                                    Integer endSize
    ){
        RespPageBean files = fileInfoService.getFiles(currentPage, size, fileInfo,beginSize == null? null :new Integer[]{beginSize*10*10*10*10*10*10,endSize == null?null: endSize*10*10*10*10*10*10});
        return files;
    }

}

