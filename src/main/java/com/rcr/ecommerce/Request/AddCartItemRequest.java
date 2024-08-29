package com.rcr.ecommerce.Request;

import lombok.Data;

@Data
public class AddCartItemRequest {
    private Long productId;
    private int quantity;
}
