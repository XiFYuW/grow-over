package com.grow.auth.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
* 角色菜单
*
* @author XiFYuW
* @since 2020-09-04
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SystemRoleMenu对象", description="角色菜单")
public class SystemRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除：0：未删除 1：删除")
    private Integer isDel;

    @ApiModelProperty(value = "角色id")
    private String systemRoleId;

    @ApiModelProperty(value = "菜单id")
    private String systemMenuId;


}
