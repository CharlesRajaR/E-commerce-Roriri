package com.rcr.ecommerce.Repository;

import com.rcr.ecommerce.Modal.ProductStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<ProductStore, Long> {

}
