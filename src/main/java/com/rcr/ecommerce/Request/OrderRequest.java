package com.rcr.ecommerce.Request;

import com.rcr.ecommerce.Modal.Address;
import lombok.Data;

@Data
public class OrderRequest {

    private Long productId;
    private Long quantity;
    private Long addressId;
}
