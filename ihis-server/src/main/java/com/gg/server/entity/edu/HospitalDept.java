package com.gg.server.entity.edu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 医院科室表
 * </p>
 *
 * @author gg
 * @since 2021-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("edu_hospital_dept")
@ApiModel(value="HospitalDept对象", description="医院科室表")
public class HospitalDept implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "所属医院Id")
    private Integer hospId;

    @ApiModelProperty(value = "科室名")
    private String deptName;

    @ApiModelProperty(value = "科室简称")
    private String shortName;

    @ApiModelProperty(value = "科室介绍")
    private String des;

    @ApiModelProperty(value = "线下科室id")
    private String deptId;


}
