package com.gg.server.service;

import com.gg.server.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gg
 * @since 2021-02-27
 */
public interface MenuService extends IService<Menu> {
    /**
     * 通过用户ID获取用户信息
     * @return
     */
    List<Menu> getMenuByDoctorId();

}
