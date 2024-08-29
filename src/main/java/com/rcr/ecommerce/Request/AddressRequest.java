package com.rcr.ecommerce.Request;

import lombok.Data;

@Data
public class AddressRequest {
    private String streetAddress;
    private String city;
    private String district;
    private String state;
    private String country;
    private Long zipcode;
}
