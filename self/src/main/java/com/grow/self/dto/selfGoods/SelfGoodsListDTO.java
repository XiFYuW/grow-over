package com.grow.self.dto.selfGoods;

import com.grow.common.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "selfGoodsListDTO", description="自营商品分页查询参数")
public class SelfGoodsListDTO extends BaseDTO {

    @ApiModelProperty(value = "自营商品编号,筛选条件")
    private String goodsNo;

    @ApiModelProperty(value = "商品名称,筛选条件")
    private String goodsName;

    @ApiModelProperty(value = "商品分类,筛选条件")
    private String goodsTypeId;




}
