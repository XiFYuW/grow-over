package com.grow.self.dto.selfGoodsColor;

import com.grow.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/02 10:06
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "selfGoodsColorListDTO", description="自营商品颜色分页查询参数")
public class SelfGoodsColorListDTO extends BaseDTO {

    @ApiModelProperty(value = "自营商品编号,筛选条件")
    private String goodsNo;
}
