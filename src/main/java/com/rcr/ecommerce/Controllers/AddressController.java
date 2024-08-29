package com.rcr.ecommerce.Controllers;

import com.rcr.ecommerce.Modal.Address;
import com.rcr.ecommerce.Modal.User;
import com.rcr.ecommerce.Request.AddressRequest;
import com.rcr.ecommerce.Services.AddressService;
import com.rcr.ecommerce.Services.UserService;
import com.zaxxer.hikari.SQLExceptionOverride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;
    @PostMapping("/address/create")
    public ResponseEntity<Address> createAddress(@RequestHeader("Authorization") String jwt,
                                                 @RequestBody AddressRequest req)throws Exception{
        User user = userService.findUserByJwtToken(jwt);

        Address address = new Address();
        address.setUser(user);
        address.setStreetAddress(req.getStreetAddress());
        address.setCity(req.getCity());
        address.setZipcode(req.getZipcode());
        address.setDistrict(req.getDistrict());
        address.setState(req.getState());
        address.setCountry(req.getCountry());

        Address createdAddress = addressService.createAddress(address);

        user.getAddresses().add(createdAddress);

        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    @GetMapping("/address/user")
    public ResponseEntity<List<Address>> getUserAddresses(@RequestHeader("Authorization") String jwt)throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        List<Address> addresses = addressService.getUserAddresses(user.getId());

        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }
}
