package com.rcr.ecommerce.Request;

import lombok.Data;

@Data
public class UpdateOrderReq {
    private String status;
    private Long orderItemId;
    private Long productId;
}
