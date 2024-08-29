package com.rcr.ecommerce.Services;

import com.rcr.ecommerce.Modal.User;

public interface UserService {
    public User findUserByJwtToken(String jwt)throws Exception;
    public User findByEmail(String email)throws Exception;
}
