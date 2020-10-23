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
@ApiModel(value = "selfGoodsAddDTO", description="自营商品添加参数")
public class SelfGoodsAddDTO implements Serializable {

    @ApiModelProperty(value = "店铺ID")
    @NotBlank(message = "店铺ID不能为空")
    private String shopId;

    @ApiModelProperty(value = "自营商品编号")
    @NotBlank(message = "商品编号不能为空")
    private String goodsNo;

    @ApiModelProperty(value = "商品名称")
    @NotBlank(message = "商品名称不能为空")
    private String goodsName;

    @ApiModelProperty(value = "商品类型")
    @NotBlank(message = "商品类型不能为空")
    private String goodsTypeId;

    @ApiModelProperty(value = "商品介绍")
    @NotBlank(message = "商品介绍不能为空")
    private String goodsIntroduce;

    @ApiModelProperty(value = "商品详情")
    @NotBlank(message = "商品详情不能为空")
    private String goodsDetails;

    @ApiModelProperty(value = "商品供货商")
    @NotBlank(message = "商品供货商不能为空")
    private String goodsSupply;

}
