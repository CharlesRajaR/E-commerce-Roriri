package com.rcr.ecommerce.Services;

import com.rcr.ecommerce.Modal.Address;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AddressService {

    public Address createAddress(Address address)throws Exception;
    public void deleteAddress(Long id)throws Exception;

    public List<Address> getUserAddresses(Long userId)throws Exception;
}
