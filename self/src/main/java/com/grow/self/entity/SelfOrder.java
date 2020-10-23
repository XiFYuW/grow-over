package com.grow.self.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
* 自营订单表
*
* @author XiFYuW
* @since 2020-08-31
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SelfOrder对象", description="自营订单表")
public class SelfOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id,自增长")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单支付方式")
    private Integer orderPayWay;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createIme;

    @ApiModelProperty(value = "代理商id")
    private String parentId;

    @ApiModelProperty(value = "买家id")
    private String userId;

    @ApiModelProperty(value = "卖家id")
    private String sellerUserId;

    @ApiModelProperty(value = "商品数量")
    private Integer commodityNumber;

    @ApiModelProperty(value = "商品金额")
    private BigDecimal commodityCash;

    @ApiModelProperty(value = "商品抵扣金额")
    private BigDecimal commodityDeduction;

    @ApiModelProperty(value = "抵扣方式")
    private Integer deductionWay;

    @ApiModelProperty(value = "商品id")
    private String commodityId;

    @ApiModelProperty(value = "商品颜色")
    private String commodityColor;

    @ApiModelProperty(value = "商品大小")
    private String commoditySize;

    @ApiModelProperty(value = "商品规格")
    private String commodityStandard;

    @ApiModelProperty(value = "店铺id")
    private String shopId;

    @ApiModelProperty(value = "订单备注")
    private String orderRemarks;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除：0：未删除 1：删除")
    private Integer isDel;


}
