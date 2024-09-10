package com.rcr.ecommerce.Request;

import com.rcr.ecommerce.Modal.USER_ROLE;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
    private USER_ROLE role;

}
