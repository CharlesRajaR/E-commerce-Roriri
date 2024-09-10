package com.rcr.ecommerce.Services;

import com.rcr.ecommerce.Modal.Address;
import com.rcr.ecommerce.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Address createAddress(Address address) throws Exception {
        Address createdAddress = new Address();
        createdAddress.setStreetAddress(address.getStreetAddress());
        createdAddress.setCity(address.getCity());
        createdAddress.setDistrict(address.getDistrict());
        createdAddress.setState(address.getState());
        createdAddress.setCountry(address.getCountry());
        createdAddress.setUser(address.getUser());


        Address savedAddress = addressRepository.save(createdAddress);

        return savedAddress;
    }

    @Override
    public void deleteAddress(Long id) throws Exception {
         addressRepository.deleteById(id);

    }

    @Override
    public List<Address> getUserAddresses(Long userId) throws Exception {
        List<Address> addresses = addressRepository.findByUserId(userId);

        return addresses;
    }
}
