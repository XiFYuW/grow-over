package com.grow.self.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
* 自营收货地址表
*
* @author XiFYuW
* @since 2020-08-31
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SelfDeliveryAddress对象", description="自营收货地址表")
@NoArgsConstructor
public class SelfDeliveryAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "收件人")
    private String recipients;

    @ApiModelProperty(value = "国家")
    private String recipientsState;

    @ApiModelProperty(value = "省份")
    private String recipientsProvince;

    @ApiModelProperty(value = "市")
    private String recipientsCity;

    @ApiModelProperty(value = "区")
    private String recipientsDistrict;

    @ApiModelProperty(value = "详细地址")
    private String recipientsDetail;

    @ApiModelProperty(value = "是否删除：0：未删除 1：删除")
    private Integer isDel;

    @ApiModelProperty(value = "收货地址id，关联查询")
    private String recipientsId;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "是否为默认地址 0：否 1：是")
    private Integer isDefault;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "收件人具体地址")
    private String recipientsAddress;

    @ApiModelProperty(value = "收件人手机号")
    private String recipientsMobile;

    /**
     * 添加
     */
    public SelfDeliveryAddress(LocalDateTime createTime, String recipients, String recipientsState,
                               String recipientsProvince, String recipientsCity, String recipientsDistrict,
                               String recipientsDetail, Integer isDel, String recipientsId, String userId,
                               Integer isDefault, String recipientsAddress, String recipientsMobile) {
        this.createTime = createTime;
        this.recipients = recipients;
        this.recipientsState = recipientsState;
        this.recipientsProvince = recipientsProvince;
        this.recipientsCity = recipientsCity;
        this.recipientsDistrict = recipientsDistrict;
        this.recipientsDetail = recipientsDetail;
        this.isDel = isDel;
        this.recipientsId = recipientsId;
        this.userId = userId;
        this.isDefault = isDefault;
        this.recipientsAddress = recipientsAddress;
        this.recipientsMobile = recipientsMobile;
    }

    /**
     * 修改
     */
    public SelfDeliveryAddress(Long id, String recipients, String recipientsState, String recipientsProvince, String recipientsCity,
                               String recipientsDistrict, String recipientsDetail, Integer isDefault, LocalDateTime updateTime,
                               String recipientsAddress, String recipientsMobile) {
        this.id = id;
        this.recipients = recipients;
        this.recipientsState = recipientsState;
        this.recipientsProvince = recipientsProvince;
        this.recipientsCity = recipientsCity;
        this.recipientsDistrict = recipientsDistrict;
        this.recipientsDetail = recipientsDetail;
        this.isDefault = isDefault;
        this.updateTime = updateTime;
        this.recipientsAddress = recipientsAddress;
        this.recipientsMobile = recipientsMobile;
    }
}
