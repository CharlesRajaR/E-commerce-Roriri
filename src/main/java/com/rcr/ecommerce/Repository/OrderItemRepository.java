package com.rcr.ecommerce.Repository;

import com.rcr.ecommerce.Modal.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItems, Long> {

      public List<OrderItems> findByStoreId(Long storeId)throws Exception;
      public List<OrderItems> findByOrderId(Long orderId)throws Exception;
}
