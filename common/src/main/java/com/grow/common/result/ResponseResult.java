package com.grow.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "后台接口统一返回")
public class ResponseResult implements Serializable {
    @ApiModelProperty(value = "响应状态码")
    public int code;

    @ApiModelProperty(value = "响应描述信息")
    public String message;

    @ApiModelProperty(value = "响应数据")
    public Object data = new Object();
}
