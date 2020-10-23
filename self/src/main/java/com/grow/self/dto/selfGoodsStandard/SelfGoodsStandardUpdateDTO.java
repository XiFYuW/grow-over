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
@ApiModel(value = "selfGoodsStandardUpdateDTO", description="自营商品规格修改参数")
public class SelfGoodsStandardUpdateDTO {
    @ApiModelProperty(value = "id", example = "0")
    @NotNull
    private Long id;

    @ApiModelProperty(value = "规格名称")
    @NotBlank(message = "规格名称不能为空")
    private String standardName;

    @ApiModelProperty(value = "规格价格")
    @NotNull(message = "规格价格不能为空")
    private BigDecimal standardPrice;
}
