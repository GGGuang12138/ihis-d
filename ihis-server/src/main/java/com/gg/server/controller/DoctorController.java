package com.gg.server.controller;


import com.gg.server.entity.Doctor;
import com.gg.server.pojo.RespBean;
import com.gg.server.service.DoctorService;
import com.gg.server.service.RoleService;
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
 * @since 2021-02-15
 */
@RestController
@RequestMapping("/system/account")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @ApiOperation(value = "查询系统账号")
    @GetMapping("/getAccounts")
    public List<Doctor> getAccounts () {
        List<Doctor> accounts = doctorService.getAccounts();
        return accounts;
    }

    @ApiOperation(value = "获取账号信息")
    @GetMapping("/getAccountById/{id}")
    public Doctor getAccountById(@PathVariable Integer id){
        Doctor accountById = doctorService.getAccountById(id);
        return accountById;
    }

    @ApiOperation(value = "添加系统账号")
    @PostMapping("/addAccount")
    public RespBean addAccount (@RequestBody Doctor doctor) {
        RespBean respBean = doctorService.updateAccount(doctor);
        return respBean;
    }

}

