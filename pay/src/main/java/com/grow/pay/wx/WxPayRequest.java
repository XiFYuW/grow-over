package com.grow.pay.wx;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * https://pay.weixin.qq.com/wiki/doc/api/index.html
 * @author https://github.com/XiFYuW
 * @date 2020/09/12 18:14
 */
@Data
@Builder
public class WxPayRequest implements Serializable {

    @ApiModelProperty("请求参数")
    private Map<String, String> params;
}
