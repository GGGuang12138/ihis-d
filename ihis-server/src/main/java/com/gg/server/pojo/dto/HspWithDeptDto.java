package com.gg.server.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: GG
 * @date: 2021/6/11 1:09 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HspWithDeptDto {

    private Integer hspId;

    private String fullName;

    private String checkBySelf;

    private String deptName;
}
