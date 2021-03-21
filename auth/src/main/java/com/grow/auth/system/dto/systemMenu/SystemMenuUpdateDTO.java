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
 * @date 2020/09/04 17:08
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description  = "系统菜单修改请求参数")
public class SystemMenuUpdateDTO implements Serializable {

    @ApiModelProperty(value = "id", example = "0", required = true)
    @NotNull
    private Integer id;

    @ApiModelProperty(value = "路径", required = true)
    @NotBlank
    private String systemPath;

    @ApiModelProperty(value = "图标", required = true)
    @NotBlank
    private String systemPic;

    @ApiModelProperty(value = "菜单名称", required = true)
    @NotBlank
    private String systemMenuName;


}
