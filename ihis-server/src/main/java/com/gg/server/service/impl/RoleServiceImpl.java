package com.gg.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gg.server.entity.Menu;
import com.gg.server.entity.MenuRole;
import com.gg.server.entity.Role;
import com.gg.server.mapper.DoctorRoleMapper;
import com.gg.server.mapper.MenuMapper;
import com.gg.server.mapper.MenuRoleMapper;
import com.gg.server.mapper.RoleMapper;
import com.gg.server.pojo.RespBean;
import com.gg.server.pojo.enums.AdminMenu;
import com.gg.server.pojo.enums.RoleType;
import com.gg.server.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gg
 * @since 2021-02-27
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuRoleMapper menuRoleMapper;
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 根据Id查询角色信息
     * @param id
     * @return
     */
    @Override
    public Role getRoleById(Integer id) {
        Role roleByIdWithMenu = roleMapper.getRoleByIdWithMenu(id);
        ArrayList<String> list = new ArrayList<>();
        for (Menu menu: roleByIdWithMenu.getMenus()){
            list.add(menu.getName());
        }
        roleByIdWithMenu.setMenusList(list);
        return roleByIdWithMenu;
    }

    @Override
    public RespBean updateRole(Role role) {
        if(role.getId() == null){
            // 新增加
            Role roleNew = new Role();
            roleNew.setName(role.getName());
            roleNew.setNameZh(role.getNameZh());
            roleNew.setDes(role.getDes());
            roleNew.setRoleType(role.getRoleType());
            if (role.getRoleType() == RoleType.HOSPITAL){
                roleNew.setHspId(role.getHspId());
            }
            roleMapper.insert(roleNew);
            // 增加相关菜单
            if (role.getMenusList().size() == 0){
                return RespBean.success("插入成功");
            }
            for (String menuName: role.getMenusList()){
                List<Menu> menus = menuMapper.selectList(new QueryWrapper<Menu>()
                        .eq("parentId", AdminMenu.valueOf(menuName)));
                for (Menu menu:menus){
                    MenuRole menuRole = new MenuRole();
                    menuRole.setMid(menu.getId());
                    menuRole.setRid(roleNew.getId());
                    menuRoleMapper.insert(menuRole);
                }
            }
        }else{
            Role roleOld = getRoleById(role.getId());
            roleOld.setName(role.getName());
            roleOld.setNameZh(role.getNameZh());
            roleOld.setDes(role.getDes());
            roleOld.setRoleType(role.getRoleType());
            if (role.getRoleType() == RoleType.HOSPITAL){
                roleOld.setHspId(role.getHspId());
            }
            roleMapper.updateById(roleOld);
            // 遍历老权限的菜单是否还在新权限的菜单
            for (String menuName: roleOld.getMenusList()){
                if(role.getMenusList().contains(menuName)){
                    // 还在新菜单里，不做更改
                }else {
                    // 不在新菜单里，删除相关菜单
                    List<Integer> drIdByNameRid = roleMapper.getDrIdByNameRId(menuName, role.getId());
                    for (Integer mrid : drIdByNameRid){
                        menuRoleMapper.deleteById(mrid);
                    }
                }
            }
            // 遍历新权限的菜单是否已经存在老的权限菜单上
            for (String menuName: role.getMenusList()){
                if(roleOld.getMenusList().contains(menuName)){
                    // 老菜单已存在不做处理
                }else {
                    // 老菜单不存在，增加相关菜单
                    List<Menu> menus = menuMapper.selectList(new QueryWrapper<Menu>()
                            .eq("parentId", AdminMenu.valueOf(menuName)));
                    for (Menu menu:menus){
                        MenuRole menuRole = new MenuRole();
                        menuRole.setMid(menu.getId());
                        menuRole.setRid(role.getId());
                        menuRoleMapper.insert(menuRole);
                    }
                }
            }
        }

        return null;
    }
}
