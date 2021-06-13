package com.gg.server.entity.edu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 医院基本信息表（主要有用的是前五个属性，后面是用来介绍医院的信息的，但是没有页面展示，所以其实没用）
 * </p>
 *
 * @author gg
 * @since 2021-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("edu_hospital_base")
@ApiModel(value="HospitalBase对象", description="医院基本信息表（主要有用的是前五个属性，后面是用来介绍医院的信息的，但是没有页面展示，所以其实没用）")
public class HospitalBase implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "医院线下唯一标识ID")
    private Integer hspId;

    @ApiModelProperty(value = "简称")
    private String simpleName;

    @ApiModelProperty(value = "全称")
    private String fullName;

    @ApiModelProperty(value = "0不开启1开启本院资讯审核")
    private Integer checkBySelf;

    @ApiModelProperty(value = "特色科室/特色介绍")
    private String feature;

    @ApiModelProperty(value = "简介")
    private String des;

    @ApiModelProperty(value = "级别")
    private String hspRank;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "科室列表")
    @TableField(exist = false)
    private List<HospitalDept> depts;

    @ApiModelProperty(value = "科室")
    @TableField(exist = false)
    private HospitalDept hospitalDept;

}
