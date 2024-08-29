package com.rcr.ecommerce.Repository;

import com.rcr.ecommerce.Modal.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findByCustomerId(Long customerId);

}
