package com.grow.auth.system.dto.systemMenu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/04 17:08
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description  = "系统菜单添加请求参数")
public class SystemMenuAddDTO implements Serializable {

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
