package com.grow.auth.system.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
* 角色
*
* @author XiFYuW
* @since 2020-09-04
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SystemRole对象", description="角色")
public class SystemRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除：0：未删除 1：删除")
    private Integer isDel;

    @ApiModelProperty(value = "角色名称")
    private String systemRoleName;

    @ApiModelProperty(value = "角色级别，0...1")
    private Integer systemRoleLevel;

    @ApiModelProperty(value = "角色id")
    private String systemRoleId;


}
