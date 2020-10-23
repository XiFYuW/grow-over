package com.grow.self.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* 自营商品表
*
* @author XiFYuW
* @date 2020-08-28
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SelfGoods对象", description="自营商品表")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelfGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "代理商id")
    private String parentId;

    @ApiModelProperty(value = "卖家id")
    private String sellerUserId;

    @ApiModelProperty(value = "店铺id")
    private String shopId;

    @ApiModelProperty(value = "自营商品编号")
    @TableField("goods_No")
    private String goodsNo;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品类型id")
    private String goodsTypeId;

    @ApiModelProperty(value = "商品介绍")
    private String goodsIntroduce;

    @ApiModelProperty(value = "商品详情")
    private String goodsDetails;

    @ApiModelProperty(value = "商品供货商")
    private String goodsSupply;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除：0：未删除 1：删除")
    private Integer isDel;


}
