package com.lz.shop_mall.controller;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.lz.shop_mall.mapper.OrderMapper;
import com.lz.shop_mall.pojo.Result;
import com.lz.shop_mall.pojo.dto.AliPay;
import com.lz.shop_mall.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/alipay")
public class AliPayController {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    @GetMapping("/pay")
    public Result<String> pay(AliPay alipay) {
        AlipayTradePagePayResponse response;
        try {
            response = Factory.Payment.Page()
                    .pay(alipay.getSubject(), alipay.getTraceNo(), alipay.getTotalAmount(), "");
        } catch (Exception e) {
            System.out.println("调用遭遇异常, 原因: " + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
        return Result.success(response.getBody());
    }

    /**
     * 处理支付宝异步通知的接口
     *
     * @param request HTTP请求对象，包含支付宝返回的通知信息
     * @return 返回给支付宝的确认信息
     */
    @PostMapping("/notify")
    public Result<String> payNotify(HttpServletRequest request) {
        // 检查交易状态是否为成功
        if ("TRADE_SUCCESS".equals(request.getParameter("trade_status"))) {
            System.out.println("=========支付宝异步回调========");

            // 存储所有请求参数
            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
            }

            // 获取交易信息
            String tradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");

            // 打印交易信息
            System.out.println("交易名称: " + params.get("subject"));
            System.out.println("交易状态: " + params.get("trade_status"));
            System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
            System.out.println("商户订单号: " + params.get("out_trade_no"));
            System.out.println("交易金额: " + params.get("total_amount"));
            System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
            System.out.println("买家付款时间: " + params.get("gmt_payment"));
            System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));

            // 在这里可以更新数据库中的订单状态等信息
            orderMapper.purchaseOrder(Integer.valueOf(tradeNo));

        }

        // 告诉支付宝，异步通知已经收到
        return Result.success("success");
    }

    @GetMapping("/order/status/{id}")
    public Result<String> getOrderStatus(@PathVariable("id") Integer id) {
        return orderService.getOrderStatus(id);
    }

}
