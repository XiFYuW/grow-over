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
* 自营商品颜色表
*
* @author XiFYuW
* @since 2020-08-31
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SelfGoodsColor对象", description="自营商品颜色表")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelfGoodsColor implements Serializable {

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

    @ApiModelProperty(value = "颜色名称")
    private String colorName;

    @ApiModelProperty(value = "颜色价格")
    private BigDecimal colorPrice;

    @ApiModelProperty(value = "颜色商品编号")
    private String colorGoodsNo;
}
