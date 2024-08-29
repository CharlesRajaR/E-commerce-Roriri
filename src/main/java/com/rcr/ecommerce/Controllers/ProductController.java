package com.rcr.ecommerce.Controllers;

import com.rcr.ecommerce.Modal.Product;
import com.rcr.ecommerce.Modal.User;
import com.rcr.ecommerce.Services.ProductService;
import com.rcr.ecommerce.Services.ProductServiceImpl;
import com.rcr.ecommerce.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(@RequestHeader("Authorization") String jwt)throws Exception{
        User user = userService.findUserByJwtToken(jwt);

        List<Product> products = productService.getAllProducts();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@RequestHeader("Authorization") String jwt, @PathVariable Long id)throws Exception{
        User user = userService.findUserByJwtToken(jwt);

        Product product = productService.findProduct(id);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
