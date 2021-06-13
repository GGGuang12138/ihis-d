package com.gg.server.controller.edu;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gg.server.config.Exception.ErrorCodeException;
import com.gg.server.entity.edu.DoctorAuth;
import com.gg.server.entity.edu.DoctorBase;
import com.gg.server.entity.edu.RefundInfo;
import com.gg.server.pojo.RespBean;
import com.gg.server.service.DoctorService;
import com.gg.server.service.edu.DoctorAuthService;
import com.gg.server.service.edu.DoctorBaseService;
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
 * @since 2021-06-12
 */
@RestController
@RequestMapping("/person/preview")
public class DoctorBaseController {

    @Autowired
    private DoctorBaseService doctorBaseService;

    @Autowired
    private DoctorAuthService doctorAuthService;

    @ApiOperation(value = "获取待审核人员")
    @GetMapping("/getNewPerson")
    public List<DoctorBase> getUncheckPerson(){
        List<DoctorBase> uncheckPerson = doctorBaseService.getUncheckPerson();
        return  uncheckPerson;
    }

    @ApiOperation(value = "获取授权信息")
    @PostMapping("/getAuthInfo")
    public DoctorAuth getAuthInfo(@RequestBody String target){
        Integer doctorId;
        try {
            JSONObject object = JSON.parseObject(target);
            doctorId = object.getInteger("target");
        } catch (Exception e) {
            throw ErrorCodeException.valueOf("错误的医生ID");
        }
        DoctorAuth authInfo = doctorAuthService.getAuthInfo(doctorId);
        return authInfo;
    }

    @ApiOperation(value = "获取基本信息")
    @PostMapping(value = "/getBaseInfo")
    public DoctorBase getDoctorBaseById(@RequestBody String target){
        Integer doctorId;
        try {
            JSONObject object = JSON.parseObject(target);
            doctorId = object.getInteger("target");
        } catch (Exception e) {
            throw ErrorCodeException.valueOf("错误的医生ID");
        }
        DoctorBase doctorBaseById = doctorBaseService.getDoctorBaseById(doctorId);
        return doctorBaseById;
    }

    @ApiOperation(value = "新增授权信息")
    @PostMapping("/addDoctorAuth")
    public RespBean addArticle(@RequestBody DoctorAuth doctorAuth){
        RespBean respBean = doctorAuthService.addDoctorAuth(doctorAuth);
        return respBean;
    }

    @ApiOperation(value = "驳回医生认证")
    @PostMapping("/refundDoctorAuth")
    public RespBean refundDoctorAuth(@RequestBody RefundInfo refundInfo){
        RespBean respBean = doctorBaseService.refundDoctorAuth(refundInfo);
        return respBean;
    }

    @ApiOperation(value = "通过医生认证")
    @PostMapping("/passDoctorAuth")
    public RespBean passDoctorAuth(@RequestBody String target){
        Integer doctorId;
        try {
            JSONObject object = JSON.parseObject(target);
            doctorId = object.getInteger("target");
        } catch (Exception e) {
            throw ErrorCodeException.valueOf("错误的医生ID");
        }
        RespBean respBean = doctorBaseService.passDoctorAuth(doctorId);
        return respBean;
    }

}

