package com.grow.self.dto.selfGoodsSize;

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
@ApiModel(value = "selfGoodsSizeAddDTO", description="自营商品尺码添加参数")
public class SelfGoodsSizeAddDTO {

    @ApiModelProperty(value = "商品编号")
    private String goodsNo;

    @ApiModelProperty(value = "尺码名称")
    @NotBlank(message = "尺码名称不能为空")
    private String sizeName;

    @ApiModelProperty(value = "尺码价格")
    @NotNull(message = "尺码价格不能为空")
    private BigDecimal sizePrice;
}
