package com.grow.self.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
* 自营订单退款交易信息表
*
* @author XiFYuW
* @since 2020-09-11
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SelfOrderDealRefund对象", description="自营订单退款交易信息表")
public class SelfOrderDealRefund implements Serializable {

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

    @ApiModelProperty(value = "交易流水号")
    private String dealSerialNumber;

    @ApiModelProperty(value = "交易时间")
    private LocalDateTime dealTime;

    @ApiModelProperty(value = "交易方式 0: 未知 1：支付宝 2：微信")
    private Integer dealWay;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "交易状态 0：初始化 1：交易成功 2：交易失败 3：交易中 ")
    private Integer dealStatus;


}
