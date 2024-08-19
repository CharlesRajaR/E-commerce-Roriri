package com.rcr.ecommerce.Request;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
