package com.lz.shop_mall.config;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AliPayConfig {
    private String appId;
    private String appPrivateKey;
    private String alipayPublicKey;
    private String notifyUrl;

    @PostConstruct
    public void init() {
        Config config = new Config();
        config.protocol = "https";

        // 测试环境
        config.gatewayHost = "openapi-sandbox.dl.alipaydev.com";

        // 开发环境
//        config.gatewayHost = "openapi.alipay.com";

        config.signType = "RSA2";
        config.appId = this.appId;
        config.merchantPrivateKey = this.appPrivateKey;
        config.alipayPublicKey = this.alipayPublicKey;
        config.notifyUrl = this.notifyUrl;
        Factory.setOptions(config);

        System.out.println("=====支付宝SDK初始化成功=====");
    }

}
