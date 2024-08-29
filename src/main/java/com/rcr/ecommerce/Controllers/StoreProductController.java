package com.rcr.ecommerce.Controllers;

import com.rcr.ecommerce.Modal.Product;
import com.rcr.ecommerce.Modal.User;
import com.rcr.ecommerce.Request.ProductRequest;
import com.rcr.ecommerce.Request.UpdateProductRequest;
import com.rcr.ecommerce.Services.ProductService;
import com.rcr.ecommerce.Services.StoreService;
import com.rcr.ecommerce.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreProductController {
    @Autowired
    private UserService userService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private ProductService productService;
    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestHeader("Authorization") String jwt,
                                                 @RequestBody ProductRequest req)throws Exception{
        User user = userService.findUserByJwtToken(jwt);

        Product product = new Product();
        product.setStore(storeService.getStore(req.getStoreId()));
        product.setName(req.getName());
        product.setPrice(Math.toIntExact(req.getPrice()));
        product.setImages(req.getImages());
        product.setDescription(req.getDescription());
        product.setQuantity(req.getQuantity());

        Product createdProduct = productService.createProduct(product);

        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/products/{storeId}")
    public ResponseEntity<List<Product>> getStoreProduct(@PathVariable Long storeId
    ,@RequestHeader("Authorization") String jwt)throws Exception{
        User user = userService.findUserByJwtToken(jwt);

        List<Product> products = productService.getStoreProduct(storeId);

        return new ResponseEntity<>(products, HttpStatus.OK);
    };

    @PutMapping("/product/update")
    public ResponseEntity<Product> updateProduct(@RequestHeader("Authorization") String jwt ,
                                                 @RequestBody UpdateProductRequest request)throws Exception{
        User user = userService.findUserByJwtToken(jwt);

        Product product = productService.updateProduct(request);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@RequestHeader("Authorization") String jwt,
                                                @PathVariable Long productId)throws Exception{
        User user = userService.findUserByJwtToken(jwt);

        productService.deleteProduct(productId);

        return new ResponseEntity<>("product with id: "+ productId +" was deleted successfully", HttpStatus.OK);
    }
}
