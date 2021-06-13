package com.gg.server.mapper;

import com.gg.server.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gg
 * @since 2021-02-27
 */
@Component
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户 id 查询对应角色列表
     * @param doctorId
     * @return
     */
    List<Role> getRoles(Integer doctorId);

    /**
     * 获取角色对应的菜单
     * @return
     */
    Role getRoleByIdWithMenu(@PathVariable("id") Integer id);

    List<Integer> getDrIdByNameRId(@PathVariable("parentName") String parentName,
                             @PathVariable("rid") Integer id);
}
