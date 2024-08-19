package com.rcr.ecommerce.Repository;

import com.rcr.ecommerce.Modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
