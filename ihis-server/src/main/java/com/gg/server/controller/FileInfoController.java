package com.gg.server.controller;


import com.gg.server.entity.FileInfo;
import com.gg.server.entity.Role;
import com.gg.server.service.FileInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

