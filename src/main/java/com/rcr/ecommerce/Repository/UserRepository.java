package com.rcr.ecommerce.Repository;

import com.rcr.ecommerce.Modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
