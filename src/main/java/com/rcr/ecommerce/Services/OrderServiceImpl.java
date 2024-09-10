package com.rcr.ecommerce.Services;

import com.rcr.ecommerce.Modal.*;
import com.rcr.ecommerce.Repository.OrderItemRepository;
import com.rcr.ecommerce.Repository.OrderRepository;
import com.rcr.ecommerce.Repository.ProductRepository;
import com.rcr.ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Order createSingleOrder(Address address, Long userId, Long quantity,   Product product) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new Exception("user is not found");
        }
        User user1 = user.get();

        Order order = new Order();
        order.setCustomer(user1);
        order.setDeliveryAddress(address);
        Order savedOrder = orderRepository.save(order);

        OrderItems item = new OrderItems();
        item.setProduct(product);
        item.setOrder(savedOrder);
        item.setQuantity(quantity);
        item.setTotalPrice(quantity * product.getPrice());
        item.setStore(product.getStore());
        item.setOrderStatus("ORDERED");

        item.setCreatedAt(LocalDateTime.now());
        OrderItems savedItem = orderItemRepository.save(item);


        return savedOrder;
    }

    @Override
    public Order createCartOrder(Cart cart, Address address, Long userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new Exception("user is not found");
        }
        User user1 = user.get();

        Order order = new Order();
        order.setCustomer(user1);
        order.setDeliveryAddress(address);
        Order savedOrder = orderRepository.save(order);

        for(CartItems cartItem: cart.getItems()){
            OrderItems item = new OrderItems();
            item.setProduct(cartItem.getProduct());
            item.setOrder(savedOrder);
            item.setQuantity((long) cartItem.getQuantity());
            item.setTotalPrice((long) cartItem.getPrice());
            item.setStore(cartItem.getProduct().getStore());
            item.setCreatedAt(LocalDateTime.now());
            item.setOrderStatus("ORDERED");
            OrderItems savedItem = orderItemRepository.save(item);
        }

        return savedOrder;
    }

    private Long total(List<OrderItems> items) {
        Long value = 0L;
        for(OrderItems item : items){
            value += item.getTotalPrice();
        }
        return value;
    }

    @Override
    public void deleteOrder(Long orderId) throws Exception {
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getUsersOrder(Long userId) throws Exception {
        List<Order> orders = orderRepository.findByCustomerId(userId);
        return orders;
    }

    @Override
    public List<OrderItems> getItemsOfOrder(Long orderId) throws Exception {
        List<OrderItems> items = orderItemRepository.findByOrderId(orderId);
        return items;
    }


    @Override
    public List<OrderItems> getStoreOrder(Long storeId) throws Exception {
        List<OrderItems> items = orderItemRepository.findByStoreId(storeId);

        return items;
    }

    @Override
    public OrderItems updateOrderStatus(String orderStatus,Long orderItemId, Long productId) throws Exception {
        Optional<OrderItems> item = orderItemRepository.findById(orderItemId);
        if(item.isEmpty()){
            throw new Exception("order item not found");
        }
        OrderItems orderItems = item.get();
        if(orderStatus == "OUT_FOR_DELIVERY"){
            Optional<Product> product = productRepository.findById(productId);
            if(product.isEmpty()){
                throw new Exception("product not found");
            }
            Product product1 = product.get();
            product1.setQuantity((int) (product1.getQuantity() - orderItems.getQuantity()));
            productRepository.save(product1);
        }
        orderItems.setOrderStatus(orderStatus);

        return orderItemRepository.save(orderItems);
    }


}
