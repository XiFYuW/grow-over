package com.grow.auth.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class SystemMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "路径")
    private String systemPath;

    @ApiModelProperty(value = "图标")
    private String systemPic;

    @ApiModelProperty(value = "菜单名称,关联")
    private String systemMenuName;

    @ApiModelProperty(value = "菜单id")
    private String systemMenuId;

    @ApiModelProperty(value = "权限标志:(user:list)")
    private String systemPermissions;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除：0：未删除 1：删除")
    private Integer isDel;

    @ApiModelProperty(value = "菜单父id")
    private Long systemMenuPid;

}
