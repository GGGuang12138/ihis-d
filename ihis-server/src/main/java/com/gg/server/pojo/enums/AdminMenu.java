package com.gg.server.pojo.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * @author: GG
 * @date: 2021/6/12 1:31 上午
 */
public enum AdminMenu implements IEnum<Integer> {
    系统管理(28,"系统管理"),
    人员审核(25,"人员审核"),
    资讯审核(22,"资讯审核"),
    资讯管理(19,"资讯管理");

    private int value;
    private String desc;
    AdminMenu(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
