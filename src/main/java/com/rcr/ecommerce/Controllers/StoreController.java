package com.rcr.ecommerce.Controllers;

import com.rcr.ecommerce.Modal.ProductStore;
import com.rcr.ecommerce.Modal.User;
import com.rcr.ecommerce.Request.StoreRequest;
import com.rcr.ecommerce.Services.StoreService;
import com.rcr.ecommerce.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private UserService userService;
    @Autowired
    private StoreService storeService;

    @PostMapping("/create")
    public ResponseEntity<ProductStore> createStore(@RequestHeader("Authorization") String jwt,
                                                    @RequestBody StoreRequest req)throws Exception{
        User user = userService.findUserByJwtToken(jwt);

        ProductStore store = new ProductStore();
        store.setOwner(user);
        store.setName(req.getName());
        store.setImages(req.getImages());
        store.setCreatedAt(LocalDateTime.now());
        store.setDescription(req.getDescription());

        ProductStore createdStore = storeService.createStore(store);

        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    @GetMapping("/owner")
    public ResponseEntity<ProductStore> getUserStore(@RequestHeader("Authorization") String jwt)throws Exception{
        User user = userService.findUserByJwtToken(jwt);

        ProductStore store = storeService.getStoreByOwnerId(user.getId());

        return new ResponseEntity<>(store, HttpStatus.OK);
    }

}
