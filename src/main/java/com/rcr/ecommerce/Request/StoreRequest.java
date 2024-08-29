package com.rcr.ecommerce.Request;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class StoreRequest {
    private String name;
    @Column(length = 1000)
    private List<String> images;
}
