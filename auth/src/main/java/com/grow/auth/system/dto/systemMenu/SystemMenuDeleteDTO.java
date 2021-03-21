package com.grow.auth.system.dto.systemMenu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author https://github.com/XiFYuW
 * @since  2020/09/04 17:08
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description  = "系统菜单删除请求参数")
public class SystemMenuDeleteDTO implements Serializable {

    @ApiModelProperty(value = "id", example = "0", required = true)
    @NotNull
    private Integer id;

}
