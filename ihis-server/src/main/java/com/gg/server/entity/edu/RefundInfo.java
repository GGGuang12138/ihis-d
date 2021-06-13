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
 * 审核驳回信息表（包括人员驳回、资讯发布驳回）
 * </p>
 *
 * @author gg
 * @since 2021-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("edu_refund_info")
@ApiModel(value="RefundInfo对象", description="审核驳回信息表（包括人员驳回、资讯发布驳回）")
public class RefundInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "唯一标识ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "审核对象Id（资讯Id/人员信息ID）")
    private Integer checkId;

    @ApiModelProperty(value = "驳回理由")
    private String reason;

    @ApiModelProperty(value = "上次驳回记录Id（第一次驳回即为空）")
    private Integer parentId;

    @ApiModelProperty(value = "资讯为0，人员为1")
    private Integer refundType;

    @ApiModelProperty(value = "审核人账号Id")
    private Integer checkAccountId;

    @ApiModelProperty(value = "审核人名")
    private String checkAccountName;


}
