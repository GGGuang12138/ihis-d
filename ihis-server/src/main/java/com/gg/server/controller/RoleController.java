package com.gg.server.controller;


import com.gg.server.entity.Menu;
import com.gg.server.entity.Role;
import com.gg.server.pojo.RespBean;
import com.gg.server.service.MenuService;
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
 * @since 2021-02-27
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "查询用户菜单列表")
    @GetMapping("/getRoles")
    public List<Role> getMenuByDoctorId(){
        return roleService.list();
    }

    @ApiOperation(value = "根据ID获取用户信息")
    @GetMapping("/getRoleById/{id}")
    public Role getRoleById(@PathVariable Integer id){
        Role roleById = roleService.getRoleById(id);
        return roleById;
    }

    @ApiOperation(value = "编辑角色信息")
    @PostMapping("/updateRole")
    public RespBean updateRole(@RequestBody Role role){
        roleService.updateRole(role);
        return RespBean.success("成功");
    }
    @ApiOperation(value = "获取角色列表")
    @GetMapping("/getRoleList")
    public List getRoleList(){
        List<Role> list = roleService.list();
        return list;
    }


}

