package com.rcr.ecommerce.Services;

import com.rcr.ecommerce.Modal.Address;
import com.rcr.ecommerce.Modal.Order;
import com.rcr.ecommerce.Modal.Product;
import com.rcr.ecommerce.Modal.User;
import com.rcr.ecommerce.Repository.OrderRepository;
import com.rcr.ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Order createOrder(List<Product> products, Address address, Long userId) throws Exception {
        int totolPrice = 0;
        for(Product product: products){
            totolPrice+=product.getPrice();
        }
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new Exception("user not found");
        }
        Order order = new Order();
        order.setProducts(products);
        order.setDelivaryAddress(address);
        order.setTotalPrice(totolPrice);
        order.setCustomer(user.get());

        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }

    @Override
    public void deleteOrder(Long orderId) throws Exception {

    }

    @Override
    public List<Order> getUsersOrder(Long userId) throws Exception {
        return null;
    }

    @Override
    public List<Order> getStoreOrder(Long storeId) throws Exception {
        return null;
    }
}
