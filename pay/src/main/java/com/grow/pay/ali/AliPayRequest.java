package com.grow.pay.ali;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * https://opendocs.alipay.com/apis
 * @author https://github.com/XiFYuW
 * @date 2020/09/12 18:14
 */
@Data
@Builder
public class AliPayRequest implements Serializable {

    @ApiModelProperty("订单标题")
    private String subject;

    @ApiModelProperty("交易创建时传入的商户订单号")
    private String outTradeNo;

    @ApiModelProperty("订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]")
    private String totalAmount;

    @ApiModelProperty("异步通知地址")
    private String asyncNotify;

    @ApiModelProperty("需要退款的金额，该金额不能大于订单金额，单位为元，支持两位小数")
    private String refundAmount;

    @ApiModelProperty("请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号")
    private String RequestNo;

    @ApiModelProperty("OpenAPI的名称，例如：alipay.trade.pay")
    private String method;

    @ApiModelProperty("没有包装在biz_content下的请求参数集合，例如app_auth_token等参数")
    private Map<String, String> textParams;

    @ApiModelProperty("被包装在biz_content下的请求参数集合")
    private Map<String, String> bizParams;
}
