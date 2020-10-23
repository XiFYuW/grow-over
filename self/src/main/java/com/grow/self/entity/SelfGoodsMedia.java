package com.grow.self.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
* 自营商品媒体
*
* @author XiFYuW
* @since 2020-10-06
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SelfGoodsMedia对象", description="自营商品媒体")
public class SelfGoodsMedia implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品媒体名称")
    private String goodsMediaName;

    @ApiModelProperty(value = "商品媒体类型1：图片 2：视频")
    private Integer goodsMediaType;

    @ApiModelProperty(value = "商品媒体访问路径")
    private String goodsMediaUrl;

    @ApiModelProperty(value = "商品媒体本地路径")
    private String goodsMediaLocal;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除：0：未删除 1：删除")
    private Integer isDel;

    @ApiModelProperty(value = "自营商品编号")
    private String goodsNo;


}
