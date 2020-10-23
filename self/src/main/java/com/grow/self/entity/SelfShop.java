package com.grow.self.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
* 自营店铺
*
* @author XiFYuW
* @since 2020-08-31
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SelfShop对象", description="自营店铺")
public class SelfShop implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "店铺id")
    private String shopId;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "店铺门头")
    private String shopPic;

    @ApiModelProperty(value = "店铺位置")
    private String shopLocation;

    @ApiModelProperty(value = "店铺经营人")
    private String shopOperator;

    @ApiModelProperty(value = "店铺认证状态：0：初始化 1：通过 2：不通过")
    private Integer shopAuthStatus;

    @ApiModelProperty(value = "店铺不通过原因")
    private String shopAuthCause;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除：0：未删除 1：删除")
    private Integer isDel;

    @ApiModelProperty(value = "是否冻结：0：未冻结 1：冻结")
    private Integer isFreeze;

    @ApiModelProperty(value = "店铺分类id")
    private String shopCategoryId;


}
