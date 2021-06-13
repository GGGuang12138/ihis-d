package com.gg.server.controller.edu;


import com.gg.server.entity.edu.HospitalBase;
import com.gg.server.pojo.RespBean;
import com.gg.server.pojo.dto.HspWithDeptDto;
import com.gg.server.pojo.param.edu.ArticleContentParam;
import com.gg.server.service.edu.HospitalBaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 医院基本信息表（主要有用的是前五个属性，后面是用来介绍医院的信息的，但是没有页面展示，所以其实没用） 前端控制器
 * </p>
 *
 * @author gg
 * @since 2021-06-11
 */
@RestController
@RequestMapping("/system/hsp")
public class HospitalBaseController {

    @Autowired
    private HospitalBaseService hospitalBaseService;

    @ApiOperation(value = "获取管理医院")
    @GetMapping("/getHsp")
    public List<HospitalBase> getHospitals(){
        return hospitalBaseService.getHospitals();
    }

    @ApiOperation(value = "获取医院")
    @GetMapping("/getHspById/{id}")
    public HospitalBase getHospitalById(@PathVariable Integer id){
        return hospitalBaseService.getHospitalById(id);
    }

    @ApiOperation(value = "修改医院信息")
    @PostMapping("/updateHsp")
    public RespBean updateHospital(@RequestBody HospitalBase hospitalBase){
        return hospitalBaseService.updateHospital(hospitalBase);
    }

    @ApiOperation(value = "修改医院列表")
    @GetMapping("/getHspList")
    public List<HospitalBase> getHospitalList(){
        return hospitalBaseService.list();
    }



}

