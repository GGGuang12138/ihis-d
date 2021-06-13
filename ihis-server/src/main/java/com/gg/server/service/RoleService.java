package com.gg.server.service;

import com.gg.server.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gg.server.pojo.RespBean;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gg
 * @since 2021-02-27
 */
@Service
public interface RoleService extends IService<Role> {

    /**
     * 通过ID获取角色信息
     *
     * @param id
     * @return
     */
    Role getRoleById(Integer id);

    /**
     * 修改/新增角色
     * @param role
     * @return
     */
    RespBean updateRole(Role role);

}
