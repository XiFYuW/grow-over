package com.grow.self.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* 自营商品规格表
*
* @author XiFYuW
* @since 2020-08-31
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SelfGoodsStandard对象", description="自营商品规格表")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelfGoodsStandard implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除：0：未删除 1：删除")
    private Integer isDel;

    @ApiModelProperty(value = "商品编号")
    private String goodsNo;

    @ApiModelProperty(value = "规格名称")
    private String standardName;

    @ApiModelProperty(value = "规格价格")
    private BigDecimal standardPrice;

    @ApiModelProperty(value = "规格商品编号")
    private String standardGoodsNo;


}
