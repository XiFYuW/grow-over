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
* 自营订单退款表
*
* @author XiFYuW
* @since 2020-08-31
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SelfOrderRefund对象", description="自营订单退款表")
public class SelfOrderRefund implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "订单id")
    private String orderNo;

    @ApiModelProperty(value = "退款原因")
    private String refundReason;

    @ApiModelProperty(value = "退款申请时间")
    private LocalDateTime refundApplyTime;

    @ApiModelProperty(value = "退款审核状态 0：已提交 1：同意  2：拒绝")
    private Integer refundCheckStatus;

    @ApiModelProperty(value = "退款时间")
    private LocalDateTime refundTime;

    @ApiModelProperty(value = "退款详情图1")
    private String refundImg1;

    @ApiModelProperty(value = "退款详情图2")
    private String refundImg2;

    @ApiModelProperty(value = "退款详情图3")
    private String refundImg3;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除：0：未删除 1：删除")
    private Integer isDel;


}
