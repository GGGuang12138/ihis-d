package com.gg.server.controller;

import com.gg.server.entity.Doctor;
import com.gg.server.pojo.RespBean;
import com.gg.server.pojo.param.DoctorLoginParam;
import com.gg.server.service.DoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

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
    public RespBean login(@RequestBody DoctorLoginParam doctorLoginParam, HttpServletRequest request){
        return doctorService.login(doctorLoginParam.getUsername(), doctorLoginParam.getPassword(), request);
    }

    @ApiOperation(value = "获取当前登陆用户信息")
    @GetMapping("/doctor/info")
    public Doctor getDoctorInfo(Principal principal){
        if (null == principal){
            return null;
        }
        String username = principal.getName();
        Doctor doctor = doctorService.getDoctorByUsername(username);
        doctor.setName(null);
        return doctor;

    }

    @ApiOperation(value = "退出登陆")
    @PostMapping("/logout")
    public RespBean logout(){
        return RespBean.success("注销成功！"); // 由前端删除token实现退出
    }
}
