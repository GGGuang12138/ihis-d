package com.gg.server.service.impl;

import com.gg.server.entity.Doctor;
import com.gg.server.entity.Menu;
import com.gg.server.mapper.MenuMapper;
import com.gg.server.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 通过用户ID获取用户信息
     * @return
     */
    @Override
    public List<Menu> getMenuByDoctorId() {
        return menuMapper.getMenuByDoctorId(((Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

}
