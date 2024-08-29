package com.rcr.ecommerce.Controllers;

import com.rcr.ecommerce.Modal.OrderItems;
import com.rcr.ecommerce.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreOrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/order/{storeId}")
    public ResponseEntity<List<OrderItems>> getStoreOrders(@RequestHeader("Authorization") String jwt,
                                                     @PathVariable Long storeId)throws Exception{
        List<OrderItems> items = orderService.getStoreOrder(storeId);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

}
