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
* 自营快递
*
* @author XiFYuW
* @since 2020-10-13
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SelfExpress对象", description="自营快递")
public class SelfExpress implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "快递公司名称")
    private String expressCompany;

    @ApiModelProperty(value = "快递公司编号")
    private String expressNumber;

    @ApiModelProperty(value = "快递公司类型0: 初始化 1：国内运输商 2:国际运输商 3:国际邮政")
    private Integer companyType;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除：0：未删除 1：删除")
    private Integer isDel;


}
