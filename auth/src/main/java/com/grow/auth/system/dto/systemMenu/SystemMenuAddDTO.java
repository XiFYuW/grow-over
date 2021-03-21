package com.grow.auth.system.dto.systemMenu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.io.Serializable;

/**
 * @author https://github.com/XiFYuW
 * @since  2020/09/04 17:08
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description  = "系统菜单添加请求参数")
public class SystemMenuAddDTO implements Serializable {

    @ApiModelProperty(value = "ID", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "菜单标题")
    private String title;

    @ApiModelProperty(value = "菜单组件名称")
    private String componentName;

    @ApiModelProperty(value = "排序")
    private Integer menuSort = 999;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "路由地址")
    private String path;

    @ApiModelProperty(value = "菜单类型: 0.目录 1.菜单 2.按钮")
    private Integer type;

    @ApiModelProperty(value = "权限标识")
    private String permission;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "缓存")
    private Boolean cache;

    @ApiModelProperty(value = "是否隐藏")
    private Boolean hidden;

    @ApiModelProperty(value = "上级菜单")
    private Integer pid;

    @ApiModelProperty(value = "外链菜单")
    private Boolean iFrame;

}
