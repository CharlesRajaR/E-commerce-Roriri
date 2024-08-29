package com.rcr.ecommerce.Controllers;

import com.rcr.ecommerce.Modal.*;
import com.rcr.ecommerce.Repository.AddressRepository;
import com.rcr.ecommerce.Request.OrderRequest;
import com.rcr.ecommerce.Services.CartService;
import com.rcr.ecommerce.Services.OrderService;
import com.rcr.ecommerce.Services.ProductService;
import com.rcr.ecommerce.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ProductService productService;
    @PostMapping("/single/order")
    public ResponseEntity<Order> createSingleOrder(@RequestHeader("Authorization") String jwt,
                                                  @RequestBody OrderRequest request)throws Exception{

        Product product = productService.findProduct(request.getProductId());
        User user = userService.findUserByJwtToken(jwt);
        Optional<Address> address = addressRepository.findById(request.getAddressId());
        Order order = orderService.createSingleOrder(address.get(),  user.getId(), request.getQuantity(), product);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/cart/order")
    public ResponseEntity<Order> createCartOrder(@RequestHeader("Authorization") String jwt,
                                                 @RequestBody Address address)throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());

        Order order = orderService.createCartOrder(cart, address, user.getId());



        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/user/order")
    public ResponseEntity<List<Order>> getUserOrder(@RequestHeader("Authorization") String jwt)throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        List<Order> order = orderService.getUsersOrder(user.getId());

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/order/items/{id}")
    public ResponseEntity<List<OrderItems>> getItemsOfOrder(@RequestHeader("Authorization") String jwt,
                                                  @PathVariable Long id)throws Exception{
        List<OrderItems> items = orderService.getItemsOfOrder(id);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @DeleteMapping("/delete/order/{id}")
    public ResponseEntity<String> deleteUserOrder(@RequestHeader("Authorization") String jwt,
                                                @PathVariable Long id)throws Exception{
        orderService.deleteOrder(id);

        return new ResponseEntity<>("order with id: "+ id +" deleted successfully", HttpStatus.OK);
    }

    }
