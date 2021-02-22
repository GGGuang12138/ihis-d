package com.gg.server.controller;

import com.gg.server.pojo.RespBean;
import com.gg.server.pojo.param.DoctorLoginParam;
import com.gg.server.service.DoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 登陆
 * @author: GG
 * @date: 2021/2/18 12:26 上午
 */
@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private DoctorService doctorService;

    @ApiOperation(value = "登陆之后返回token")
    @PostMapping("/login")
    public RespBean login(DoctorLoginParam doctorLoginParam, HttpServletRequest request){
        return doctorService.login(doctorLoginParam.getUsername(), doctorLoginParam.getPassword(), request);
    }
}
