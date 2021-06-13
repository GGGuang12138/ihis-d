package com.gg.server.pojo.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * @author: GG
 * @date: 2021/6/1 7:04 下午
 */
public enum AccountType implements IEnum<Integer> {

    SYSTEM(0,"平台管理员账号"),
    HOSPITAL(1,"医院管理员账号"),
    DOCTOR(2,"医生账号");

    private int value;
    private String desc;
    AccountType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
