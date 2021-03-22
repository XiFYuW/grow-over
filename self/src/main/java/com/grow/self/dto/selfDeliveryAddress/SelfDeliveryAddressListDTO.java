package com.grow.self.dto.selfDeliveryAddress;

import com.grow.common.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/08/29 16:46
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description="自营收货地址分页查询请求参数")
public class SelfDeliveryAddressListDTO extends BaseDTO {

    @ApiModelProperty(value = "用户id,筛选条件")
    private String userId;

}
