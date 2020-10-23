package com.grow.self.dto.selfDeliveryAddress;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
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
@ApiModel(description="自营收货地址修改参数")
public class SelfDeliveryAddressUpdateDTO implements Serializable {

    @ApiModelProperty(value = "id", example = "0")
    @NotNull
    private Long id;

    @ApiModelProperty(value = "收件人")
    @NotBlank
    private String recipients;

    @ApiModelProperty(value = "国家")
    @NotBlank
    private String recipientsState;

    @ApiModelProperty(value = "省份")
    @NotBlank
    private String recipientsProvince;

    @ApiModelProperty(value = "市")
    @NotBlank
    private String recipientsCity;

    @ApiModelProperty(value = "区")
    @NotBlank
    private String recipientsDistrict;

    @ApiModelProperty(value = "详细地址")
    private String recipientsDetail;

    @ApiModelProperty(value = "是否为默认地址 0：否 1：是", example = "0")
    private Integer isDefault;

    @ApiModelProperty(value = "收件人手机号")
    @NotBlank
    private String recipientsMobile;
}
