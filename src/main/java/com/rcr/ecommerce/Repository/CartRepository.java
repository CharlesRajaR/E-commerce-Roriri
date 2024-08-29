package com.rcr.ecommerce.Repository;

import com.rcr.ecommerce.Modal.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    public Cart findByCustomerId(Long id);
}
