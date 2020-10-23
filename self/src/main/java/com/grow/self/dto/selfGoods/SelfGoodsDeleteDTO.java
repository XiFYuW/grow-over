package com.grow.self.dto.selfGoods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "selfGoodsDeleteDTO", description="自营商品删除参数")
public class SelfGoodsDeleteDTO implements Serializable {
    @ApiModelProperty(value = "id", example = "0")
    @NotNull
    private Long id;



}
