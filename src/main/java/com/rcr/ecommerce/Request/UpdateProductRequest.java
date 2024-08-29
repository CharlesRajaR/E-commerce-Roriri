package com.rcr.ecommerce.Request;

import lombok.Data;

@Data
public class UpdateProductRequest {
    private Long productId;
    private int quantity;
}
