package com.rcr.ecommerce.Repository;

import com.rcr.ecommerce.Modal.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    public List<Address> findByUserId(Long id);
}
