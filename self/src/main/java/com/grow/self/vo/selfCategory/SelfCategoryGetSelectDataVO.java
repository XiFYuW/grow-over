package com.grow.self.vo.selfCategory;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/01 16:48
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "下拉自营商品分类返回数据")
public class SelfCategoryGetSelectDataVO implements Serializable {

    @ApiModelProperty(value = "下拉列表名称")
    private String name;

    @ApiModelProperty(value = "下拉列表值")
    private String value;

    @ApiModelProperty(value = "代理商id", example = "0")
    private Integer isSon;

}
