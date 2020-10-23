package com.grow.self.dto.selfGoodsSize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "selfGoodsSizeDeleteDTO", description="自营商品尺码删除参数")
public class SelfGoodsSizeDeleteDTO {

    @ApiModelProperty(value = "id", example = "0")
    @NotNull
    private Long id;
}
