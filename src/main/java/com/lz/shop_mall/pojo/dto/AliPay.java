package com.lz.shop_mall.pojo.dto;

import lombok.Data;

/**
 * 支付宝支付实体类
 */
@Data
public class AliPay {

    /**
     * 追踪号：用于唯一标识一次支付请求，可以是订单号或其他业务相关的唯一标识
     */
    private String traceNo;

    /**
     * 总金额：支付的总金额，单位为元
     */
    private String totalAmount;

    /**
     * 订单标题：支付时显示的订单名称或描述
     */
    private String subject;
}

