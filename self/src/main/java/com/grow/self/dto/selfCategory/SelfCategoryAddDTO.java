package com.grow.self.dto.selfCategory;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/08/31 17:45
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description  = "商品分类添加请求参数")
public class SelfCategoryAddDTO implements Serializable {

    @ApiModelProperty(value = "类别图片")
    @NotBlank
    private String categoryPic;

    @ApiModelProperty(value = "代理商id")
    @NotBlank
    private String parentId;

    @ApiModelProperty(value = "店铺id")
    @NotBlank
    private String shopId;

    @ApiModelProperty(value = "分类等级，默认为0（顶级）,几级就传几", example = "0")
    private Integer categoryLevel;

    @ApiModelProperty(value = "分类父级id，从/selfCategory/getSelectData接口取数据")
    private String categoryParentId;

    @ApiModelProperty(value = "类别名称")
    @NotBlank
    private String categoryName;
}
