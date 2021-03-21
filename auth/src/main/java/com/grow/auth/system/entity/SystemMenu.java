package com.grow.auth.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
* 系统菜单
*
* @author XiFYuW
* @since 2020-09-04
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SystemMenu对象", description="系统菜单")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "路由地址")
    private String systemPath;

    @ApiModelProperty(value = "图标")
    private String systemPic;

    @ApiModelProperty(value = "菜单名称")
    private String systemMenuName;

    @ApiModelProperty(value = "权限标志:(user:list)")
    private String systemPermissions;

    @ApiModelProperty(value = "菜单类型: 0.目录 1.菜单 2.按钮")
    private Integer systemMenuType;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除：0：未删除 1：删除")
    private Integer isDel;

    @ApiModelProperty(value = "菜单父id")
    private Integer systemMenuPid;

    @ApiModelProperty(value = "组件路径")
    private String systemComponent;

    @ApiModelProperty(value = "组件名称")
    private String systemComponentName;

    @ApiModelProperty(value = "菜单排序")
    private Integer systemMenuSort;

    @ApiModelProperty(value = "是否外链 bit(1) default 0")
    private Boolean iFrame;

    @ApiModelProperty(value = "缓存 bit(1) default 0")
    private Boolean isCache;

    @ApiModelProperty(value = "隐藏 bit(1) default 0")
    private Boolean hidden;

    @ApiModelProperty(value = "子菜单数目")
    private Integer subCount;

    @TableField(exist = false)
    private List<SystemMenu> children;

    public Boolean getHasChildren() {
        return subCount > 0;
    }

    public Boolean getLeaf() {
        return subCount <= 0;
    }

    public String getLabel() {
        return systemMenuName;
    }
}
