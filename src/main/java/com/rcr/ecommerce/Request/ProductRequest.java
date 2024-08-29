package com.rcr.ecommerce.Request;

import lombok.Data;

import java.util.List;

@Data
public class ProductRequest {
    private String name;
    private List<String> images;
    private String description;
    private Long price;
    private int quantity;
    private Long storeId;
}
