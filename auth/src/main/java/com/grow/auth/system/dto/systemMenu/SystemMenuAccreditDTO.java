package com.grow.auth.system.dto.systemMenu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/05 11:50
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ApiModel(description = "系统菜单授权请求参数")
public class SystemMenuAccreditDTO implements Serializable {

    @NotBlank
    @ApiModelProperty(value = "权限标志:(user:list)", required = true)
    private String systemPermissions;

    @ApiModelProperty(value = "id", example = "0", required = true)
    @NotNull
    private Long id;
}
