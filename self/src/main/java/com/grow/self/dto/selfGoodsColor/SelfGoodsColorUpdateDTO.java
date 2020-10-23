package com.grow.self.dto.selfGoodsColor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/02 10:10
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "selfGoodsColorUpdateDTO", description="自营商品颜色修改参数")
public class SelfGoodsColorUpdateDTO {

    @ApiModelProperty(value = "id", example = "0")
    @NotNull
    private Long id;

    @ApiModelProperty(value = "颜色名称")
    @NotBlank(message = "颜色名称不能为空")
    private String colorName;

    @ApiModelProperty(value = "颜色价格")
    @NotNull(message = "颜色价格不能为空")
    private BigDecimal colorPrice;
}
