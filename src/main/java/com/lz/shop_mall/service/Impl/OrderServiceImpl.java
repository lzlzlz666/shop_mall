package com.lz.shop_mall.service.Impl;

import com.lz.shop_mall.mapper.OrderMapper;
import com.lz.shop_mall.mapper.ProductMapper;
import com.lz.shop_mall.pojo.Order;
import com.lz.shop_mall.pojo.Product;
import com.lz.shop_mall.pojo.Result;
import com.lz.shop_mall.pojo.UserAddress;
import com.lz.shop_mall.pojo.dto.OrderProduct;
import com.lz.shop_mall.pojo.dto.ProductDTO;
import com.lz.shop_mall.service.OrderService;
import com.lz.shop_mall.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;

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

    /**
     * 获取我的订单板块下面，所有的订单信息
     * @return
     */
    public Result<List<OrderProduct>> getMyOrders() {
        List<OrderProduct> orderProductList = new ArrayList<>();

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        List<Order> orderList = orderMapper.getOrders(id);
        if(orderList == null) {
            return Result.error("该用户不存在订单信息");
        }

        for (Order order : orderList) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setId(String.valueOf(order.getOrderId()));
            orderProduct.setCreateTime(order.getCreateTime());
            orderProduct.setPayMoney(order.getProductCount() * order.getProductPrice());
            orderProduct.setPostFee(10);
            orderProduct.setPaid(order.getIsPay());

            // 获取对应 家庭地址 和 电话号码
            Integer addressId = order.getAddressId();
            UserAddress address = orderMapper.getAddressByUserAddressId(addressId);
            orderProduct.setAddress(address.getAddress());
            orderProduct.setContact(address.getContact());

            Integer productId = order.getProductId();
            Product product = productMapper.getProduct(Long.valueOf(productId));

            // 使用 Builder 快速赋值
            ProductDTO productDTO = ProductDTO.builder()
                    .productId(product.getProductId())  // 假设Product有对应的getter方法
                    .categoryId(product.getCategoryId())
                    .productName(product.getProductName())
                    .productDescription(product.getProductDescription())
                    .productOriginalPrice(product.getProductOriginalPrice())
                    .productPrice(product.getProductPrice())
                    .productStock(product.getProductStock())
                    .productSales(product.getProductSales())
                    .productImg(product.getProductImg())
                    .productFormat(order.getProductFormat())
                    .createTime(product.getCreateTime())
                    .updateTime(product.getUpdateTime())
                    .build();

            productDTO.setQuantity(order.getProductCount());

            List<ProductDTO> productList = new ArrayList<>();
            productList.add(productDTO);
            orderProduct.setSkus(productList);

            orderProductList.add(orderProduct);
        }

        return Result.success(orderProductList);
    }

    /**
     * 取消订单
     * @param id
     * @return
     */
    public Result delete(Integer id) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        orderMapper.delete(id, userId);

        return Result.success("删除订单成功");
    }

    /**
     * 根据id 删除数据库中地址信息
     * @param id
     * @return
     */
    public Result deleteAddress(Integer id) {
        orderMapper.deleteAddress(id);

        return Result.success("删除地址信息成功!");
    }
}
