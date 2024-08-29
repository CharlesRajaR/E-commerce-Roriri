package com.rcr.ecommerce.Controllers;

import com.rcr.ecommerce.Modal.ProductStore;
import com.rcr.ecommerce.Modal.User;
import com.rcr.ecommerce.Repository.StoreRepository;
import com.rcr.ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminStoreController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StoreRepository storeRepository;
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStore(@RequestHeader("Authorization") String jwt,
                                              @PathVariable Long id)throws Exception{
        storeRepository.deleteById(id);
        return new ResponseEntity<>("store with id: "+ id +" is deleted successfully", HttpStatus.OK);
    }
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<String> deleteUser(@RequestHeader("Authorization") String jwt,
                                             @PathVariable Long id)throws Exception{
        userRepository.deleteById(id);

        return new ResponseEntity<>("user with id : "+ id +" deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser(@RequestHeader("Authorization") String jwt)throws Exception{

        List<User> users = userRepository.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/size")
    public ResponseEntity<Long> getAllUserSize(@RequestHeader("Authorization") String jwt)throws Exception{

//        List<User> users = userRepository.findAll();

        return new ResponseEntity<>(userRepository.count(), HttpStatus.OK);
    }

    @GetMapping("/stores/size")
    public ResponseEntity<Long> getAllStoreSize(@RequestHeader("Authorization") String jwt)throws Exception{

        Long count = storeRepository.count();
//        List<ProductStore> stores = storeRepository.findAll();

        return new ResponseEntity<>( count, HttpStatus.OK);
    }
}
