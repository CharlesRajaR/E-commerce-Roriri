package com.rcr.ecommerce.Request;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class StoreRequest {
    private String name;
    private String description;
    @Column(length = 1000)
    private List<String> images;
}
