package com.gg.server.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
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
 * @since 2021-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("upload_file_info")
@ApiModel(value="FileInfo对象", description="")
public class FileInfo implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    @TableField("alias_path")
    private String aliasPath;

    @TableField("base_path")
    private String basePath;

    @TableField("file_size")
    private String fileSize;

    @TableField("merge_time")
    private LocalDateTime mergeTime;

    @TableField("original_name")
    private String originalName;

    @TableField("partner_id")
    private String partnerId;

    private String postfix;

    @TableField("upload_time")
    private LocalDateTime uploadTime;

    private String url;


}
