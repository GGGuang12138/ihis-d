package com.gg.server.pojo.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * @author: GG
 * @date: 2021/6/1 7:04 下午
 */
public enum RoleType implements IEnum<Integer> {

    SYSTEM(0,"平台管理员角色"),
    HOSPITAL(1,"医院管理员角色");

    private int value;
    private String desc;
    RoleType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
