package com.gg.server.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.gg.server.pojo.enums.RoleType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author gg
 * @since 2021-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("d_role")
@ApiModel(value="Role对象", description="")
public class Role implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "角色中文名称")
    private String nameZh;

    @ApiModelProperty(value = "角色类型")
    private RoleType roleType;

    @ApiModelProperty(value = "管理医院ID")
    private Integer hspId;

    @ApiModelProperty(value = "角色描述")
    private String des;

    @ApiModelProperty(value = "管理菜单")
    @TableField(exist = false)
    private List<Menu> menus;

    @ApiModelProperty(value = "菜单数组")
    @TableField(exist = false)
    private List<String> menusList;



}
