package com.gg.server.entity.edu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author gg
 * @since 2021-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("edu_doctor_base")
@ApiModel(value="DoctorBase对象", description="")
public class DoctorBase implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "医生Id")
    private Integer id;

    @ApiModelProperty(value = "医生姓名")
    private String name;

    @ApiModelProperty(value = "医生性别")
    private Boolean sex;

    @ApiModelProperty(value = "归属医院Id")
    private Integer hspId;

    @ApiModelProperty(value = "线下工号")
    private String offLineId;

    @ApiModelProperty(value = "职称Id")
    private Integer titleId;

    @ApiModelProperty(value = "职称名")
    private String titleName;

    @ApiModelProperty(value = "科室Id")
    private Integer deptId;

    @ApiModelProperty(value = "科室名")
    private String deptName;

    @ApiModelProperty(value = "简介")
    private String des;

    @ApiModelProperty(value = "擅长")
    private String better;

    @ApiModelProperty(value = "状态")
    private Integer state;

    @ApiModelProperty(value = "归属医院名")
    private String hspName;

    @ApiModelProperty(value = "头像Url")
    private String coverUrl;

    @ApiModelProperty(value = "关联医生认证表的医生Id")
    private Integer authId;

    @ApiModelProperty(value = "最后修改时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "类别")
    private String level;

    @ApiModelProperty(value = "国家类别")
    private String nationLevel;

    @ApiModelProperty(value = "医生账号ID")
    private Integer did;


}
