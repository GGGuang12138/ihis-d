package com.gg.server.pojo.enums;


import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * @author: GG
 * @date: 2021/5/7 1:36 下午
 */
public enum DoctorStatus implements IEnum<Integer> {

    NEW(0,"未提交审核"),
    PASS(1,"审核通过"),
    WAIT_REVIEW(2,"审核中"),
    FAIL(3,"审核未通过");

    DoctorStatus(int value, String desc){
        this.value = value;
        this.desc = desc;
    }

    private int value;
    private String desc;

    @Override
    public Integer getValue() {
        return this.value;
    }
}
