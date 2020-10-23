package com.grow.self.dto.selfGoods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "selfGoodsUpdateDTO", description="自营商品修改参数")
public class SelfGoodsUpdateDTO implements Serializable {

    @ApiModelProperty(value = "id", example = "0")
    private Long id;

    @ApiModelProperty(value = "商品名称")
    @NotBlank
    private String goodsName;

    @ApiModelProperty(value = "商品类型id")
    @NotBlank
    private String goodsTypeId;

    @ApiModelProperty(value = "商品介绍")
    private String goodsIntroduce;

    @ApiModelProperty(value = "商品详情")
    private String goodsDetails;

    @ApiModelProperty(value = "商品供货商")
    private String goodsSupply;

}
