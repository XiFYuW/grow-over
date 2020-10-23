package com.grow.pay.wx;

import com.grow.pay.wx.tool.WXPay;
import com.grow.pay.wx.tool.WxConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 8:38
 */
@Configuration
public class WxFactoryConfig {

    @Bean(name = "wxConfig")
    public WxConfig wxConfig() throws IOException {
       return new WxConfig("", "", "", "");
    }

    @Bean(name = "wxPay")
    public WXPay wxPay() throws Exception {
        return new WXPay(wxConfig());
    }
}
