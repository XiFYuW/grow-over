package com.grow.self.dto.selfOrderDealPay;

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
@ApiModel(value = "selfOrderDealPayListDTO", description="自营订单付款交易信息分页查询参数")
public class SelfOrderDealPayListDTO extends BaseDTO {
    @ApiModelProperty(value = "自营订单交易流水号")
    private String dealSerialNumber;

    @ApiModelProperty(value = "自营订单交易订单号")
    private String orderNo;

    @ApiModelProperty(value = "自营订单交易方式")
    private Integer dealWay;


}
