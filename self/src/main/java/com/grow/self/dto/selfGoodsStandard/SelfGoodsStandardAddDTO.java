package com.grow.self.dto.selfGoodsStandard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "selfGoodsStandardAddDTO", description="自营商品规格添加参数")
public class SelfGoodsStandardAddDTO {

    @ApiModelProperty(value = "商品编号")
    private String goodsNo;

    @ApiModelProperty(value = "规格名称")
    @NotBlank(message = "规格名称不能为空")
    private String standardName;

    @ApiModelProperty(value = "规格价格")
    @NotNull(message = "规格价格不能为空")
    private BigDecimal standardPrice;

}
