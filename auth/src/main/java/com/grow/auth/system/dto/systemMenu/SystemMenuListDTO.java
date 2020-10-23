package com.grow.auth.system.dto.systemMenu;

import com.grow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/04 17:08
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description  = "系统菜单分页查询请求参数")
public class SystemMenuListDTO extends BaseDTO implements Serializable {

    @ApiModelProperty(value = "路径,筛选条件")
    private String systemPath;

    @ApiModelProperty(value = "菜单名称,筛选条件")
    private String systemMenuName;

    @ApiModelProperty(value = "id(筛选该id下的所有子数据项),筛选条件", example = "0")
    private Long id;

}
