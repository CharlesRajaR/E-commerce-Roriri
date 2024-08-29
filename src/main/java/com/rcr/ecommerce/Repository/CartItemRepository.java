package com.rcr.ecommerce.Repository;

import com.rcr.ecommerce.Modal.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItems, Long> {
}
