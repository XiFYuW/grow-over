package com.grow.pay.ali;

import com.alipay.easysdk.kernel.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/13 8:38
 */
@Configuration
public class AliFactoryConfig {

    @Bean(name = "ailConfig")
    public Config config() {
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = "openapi.alipay.com";
        config.signType = "RSA2";

        // <-- 请填写您的AppId，例如：2019091767145019 -->
        config.appId = "*";

        // <-- 请填写您的应用私钥，例如：MIIEvQIBADANB ... ... -->
        config.merchantPrivateKey = "*";

        // 注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
        // <-- 请填写您的应用公钥证书文件路径，例如：/foo/appCertPublicKey_2019051064521003.crt -->
        //config.merchantCertPath = "";
        // <-- 请填写您的支付宝公钥证书文件路径，例如：/foo/alipayCertPublicKey_RSA2.crt -->
        //config.alipayCertPath = "";
        // <-- 请填写您的支付宝根证书文件路径，例如：/foo/alipayRootCert.crt -->
        //config.alipayRootCertPath = "";

        // 注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
        // <-- 请填写您的支付宝公钥，例如：MIIBIjANBg... -->
        config.alipayPublicKey = "*";

        // 可设置异步通知接收服务地址（可选） <-- 请填写您的支付类接口异步通知接收服务地址，例如：https://www.test.com/callback -->
        config.notifyUrl = "";

        //可设置AES密钥，调用AES加解密相关接口时需要（可选） <-- 请填写您的AES密钥，例如：aa4BtZ4tspm2wnXLb1ThQA== -->
        config.encryptKey = "";

        return config;
    }
}
