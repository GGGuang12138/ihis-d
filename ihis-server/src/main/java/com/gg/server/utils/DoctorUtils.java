package com.gg.server.utils;

import com.gg.server.entity.Doctor;
        import org.springframework.security.core.context.SecurityContextHolder;

/** 操作员工具类 - 获取当前登录用户
 * @author gg
 * @create 2021/5/7-下午4:43
 */
public class DoctorUtils {

    public static Doctor getCurrentAdmin(){
        return (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
