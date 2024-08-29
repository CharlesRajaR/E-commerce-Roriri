package com.rcr.ecommerce.Repository;

import com.rcr.ecommerce.Modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByStoreId(Long storeId);
}
