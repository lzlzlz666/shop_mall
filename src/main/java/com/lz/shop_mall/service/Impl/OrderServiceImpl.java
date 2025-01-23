package com.lz.shop_mall.service.Impl;

import com.lz.shop_mall.mapper.OrderMapper;
import com.lz.shop_mall.pojo.Order;
import com.lz.shop_mall.pojo.Result;
import com.lz.shop_mall.pojo.UserAddress;
import com.lz.shop_mall.service.OrderService;
import com.lz.shop_mall.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 获取当前用户已存在的订单地址信息
     * @return
     */
    public Result<List<UserAddress>> list() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        List<UserAddress> userAddressList = orderMapper.list(id);

        if (userAddressList == null) {
            return Result.error("未找到地址信息");
        }

        return Result.success(userAddressList);
    }

    /**
     * 更新选中的默认地址
     * @param userAddressId
     * @return
     */
    public Result update(Integer userAddressId) {

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        // 更新地址：首先将所有地址的 defaultAddress 设置为 0
        orderMapper.updateAllAddressToNotDefault(id);

        // 然后将选中的地址的 defaultAddress 设置为 1
        orderMapper.updateAddressToDefault(userAddressId);

        return Result.success();
    }

    /**
     * 添加地址信息
     * @param userAddress
     * @return
     */
    public Result add(UserAddress userAddress) {
        UserAddress userAddress1 = new UserAddress();

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        if (userAddress.getReceiverName() == null || userAddress.getReceiverName().trim().isEmpty() ||
                userAddress.getContact() == null || userAddress.getContact().trim().isEmpty() ||
                userAddress.getAddress() == null || userAddress.getAddress().trim().isEmpty()) {
            return Result.error("地址信息未填写完整！");
        }


        userAddress1.setUserId(id);
        userAddress1.setReceiverName(userAddress.getReceiverName());
        userAddress1.setContact(userAddress.getContact());
        userAddress1.setAddress(userAddress.getAddress());
        userAddress1.setDefaultAddress(1);
        userAddress1.setCreateTime(LocalDateTime.now());
        userAddress1.setUpdateTime(LocalDateTime.now());

        orderMapper.add(userAddress1);

        return Result.success();
    }

    /**
     * 生成订单
     * @param order
     * @return
     */
    public Result<Order> generateOrder(Order order) {
        Order order1 = new Order();

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        Integer addressId = orderMapper.getAddressId(id);

        order1.setUserId(id);
        order1.setAddressId(addressId);
        order1.setProductId(order.getProductId());
        order1.setProductCount(order.getProductCount());
        order1.setProductFormat(order.getProductFormat());
        order1.setProductPrice(order.getProductPrice());
        order1.setIsPay(0);
        order1.setCreateTime(LocalDateTime.now());
        order1.setUpdateTime(LocalDateTime.now());
        orderMapper.generateOrder(order1);

        return Result.success(order1);
    }

    /**
     * 对多个订单进行购买
     * @param listOrders
     * @return
     */
    public Result purchaseOrder(List<Order> listOrders) {

        // 遍历订单列表
        for (Order order : listOrders) {
            Integer orderId = order.getOrderId();
            orderMapper.purchaseOrder(orderId);
        }

        return Result.success();
    }
}
