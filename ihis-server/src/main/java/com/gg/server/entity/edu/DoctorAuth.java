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
 * 医生身份认证信息表，数据进行加密处理
 * </p>
 *
 * @author gg
 * @since 2021-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("edu_doctor_auth")
@ApiModel(value="DoctorAuth对象", description="医生身份认证信息表，数据进行加密处理")
public class DoctorAuth implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "唯一标识ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "对于医生账号表的ID")
    private Integer did;

    @ApiModelProperty(value = "身份证号码")
    private String idCardNum;

    @ApiModelProperty(value = "身份证正面")
    private String idCardImgFront;

    @ApiModelProperty(value = "身份证方面")
    private String idCardImgBack;

    @ApiModelProperty(value = "毕业证书编号")
    private String graduateNum;

    @ApiModelProperty(value = "毕业证正面")
    private String graduateImgFront;

    @ApiModelProperty(value = "执业证书编号")
    private String practiceNum;

    @ApiModelProperty(value = "执业证书")
    private String practiceImg;

    @ApiModelProperty(value = "资格证编号")
    private String certificateNum;

    @ApiModelProperty(value = "资格证书")
    private String certificateImg;

    @ApiModelProperty(value = "资格证书类型名称")
    private String certificateTypeName;

    @ApiModelProperty(value = "工作证明材料")
    private String workImg;


}
