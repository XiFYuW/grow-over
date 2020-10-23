package com.grow.self.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
* 自营店铺分类
*
* @author XiFYuW
* @since 2020-08-31
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SelfShopCategory对象", description="自营店铺分类")
public class SelfShopCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除：0：未删除 1：删除")
    private Integer isDel;

    @ApiModelProperty(value = "店铺分类id")
    private String shopCategoryId;

    @ApiModelProperty(value = "店铺分类名称")
    private String shopCategoryName;


}
