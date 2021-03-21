package com.grow.auth.system.dto.systemMenu;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class SystemMenuChildrenListDTO {

    @ApiModelProperty(value = "父菜单id")
    @NotNull(message = "父菜单id不能为空")
    private Integer pid;
}
