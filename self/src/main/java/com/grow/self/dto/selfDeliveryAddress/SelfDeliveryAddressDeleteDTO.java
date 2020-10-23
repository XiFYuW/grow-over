package com.grow.self.dto.selfDeliveryAddress;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/08/29 16:46
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description="自营收货地址删除参数")
public class SelfDeliveryAddressDeleteDTO implements Serializable {

    @ApiModelProperty(value = "id", example = "0")
    @NotNull
    private Long id;
}
