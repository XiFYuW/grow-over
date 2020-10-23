package com.grow.auth.system.dto.systemMenu;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/05 10:29
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemMenuSelectTreeDataDTO implements Serializable {

    @ApiModelProperty(value = "菜单父id,默认为0", example = "0", required = true)
    @NotNull
    private Long systemMenuPid;
}
