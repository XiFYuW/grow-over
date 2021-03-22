package com.grow.self.dto.selfGoodsStandard;

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
@ApiModel(value = "selfGoodsStandardListDTO", description="自营商品规格分页查询参数")
public class SelfGoodsStandardListDTO extends BaseDTO {
    @ApiModelProperty(value = "自营商品编号,筛选条件")
    private String goodsNo;
}
