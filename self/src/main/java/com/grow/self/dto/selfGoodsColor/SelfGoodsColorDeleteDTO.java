package com.grow.self.dto.selfGoodsColor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/02 10:06
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "selfGoodsColorDeleteDTO", description="自营商品颜色删除参数")
public class SelfGoodsColorDeleteDTO {

    @ApiModelProperty(value = "id", example = "0")
    @NotNull
    private Long id;
}
