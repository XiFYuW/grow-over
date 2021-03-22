package com.grow.self.dto.selfCategory;

import com.grow.common.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/01 15:21
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description  = "获取下拉选择商品分类请求参数")
public class SelfCategoryGetSelectDataDTO extends BaseDTO {

    @ApiModelProperty(value = "代理商id")
    @NotBlank
    private String parentId;

    @ApiModelProperty(value = "店铺id")
    @NotBlank
    private String shopId;

    @ApiModelProperty(value = "分类等级，默认为0（顶级）,几级就传几", example = "0")
    @NotNull
    private Integer categoryLevel;

    @ApiModelProperty(value = "分类父级id，从/selfCategory/getSelectData接口取数据，当categoryLevel为0是不用传")
    private String categoryParentId;
}
