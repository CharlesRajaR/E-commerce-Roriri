package com.rcr.ecommerce.Services;

import com.rcr.ecommerce.Modal.Address;
import com.rcr.ecommerce.Modal.Order;
import com.rcr.ecommerce.Modal.Product;

import java.util.List;

public interface OrderService {
    public Order createOrder(List<Product> products, Address address, Long userId)throws Exception;
    public void deleteOrder(Long orderId)throws Exception;

    public List<Order> getUsersOrder(Long userId)throws Exception;
    public List<Order> getStoreOrder(Long storeId)throws Exception;
}
