package com.rcr.ecommerce.Services;

import com.rcr.ecommerce.Modal.*;

import java.util.List;

public interface OrderService {
    public Order createSingleOrder(Address address,Long quantity, Long userId, Product product)throws Exception;
    public Order createCartOrder(Cart cart, Address address, Long userId)throws Exception;
    public void deleteOrder(Long orderId)throws Exception;

    public List<Order> getUsersOrder(Long userId)throws Exception;
    public List<OrderItems> getItemsOfOrder(Long orderId)throws Exception;
    public List<OrderItems> getStoreOrder(Long storeId)throws Exception;

    public OrderItems updateOrderStatus(String orderStatus,Long orderItemId, Long productId)throws Exception;
}
