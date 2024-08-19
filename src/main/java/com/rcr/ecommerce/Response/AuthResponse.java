package com.rcr.ecommerce.Response;

import com.rcr.ecommerce.Modal.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
