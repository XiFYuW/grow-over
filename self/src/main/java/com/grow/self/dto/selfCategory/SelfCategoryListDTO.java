package com.grow.self.dto.selfCategory;

import com.grow.common.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/08/29 16:45
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description  = "商品分类分页查询请求参数")
public class SelfCategoryListDTO extends BaseDTO {

    @ApiModelProperty(value = "店铺id,筛选条件")
    private String shopId;

    @ApiModelProperty(value = "类别名称,筛选条件")
    private String categoryName;

    @ApiModelProperty(value = "分类等级,筛选条件", example = "-1")
    private Integer categoryLevel;

    @ApiModelProperty(value = "分类父级id,筛选条件")
    private String categoryParentId;
}
