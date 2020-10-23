package com.grow.self.dto.selfCategory;

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
 * @date 2020/08/31 17:45
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description  = "商品分类删除请求参数")
public class SelfCategoryDeleteDTO implements Serializable {

    @ApiModelProperty(value = "id", example = "0")
    @NotNull
    private Long id;

}
