package com.rcr.ecommerce.Controllers;

import com.rcr.ecommerce.Modal.Cart;
import com.rcr.ecommerce.Modal.CartItems;
import com.rcr.ecommerce.Modal.User;
import com.rcr.ecommerce.Request.AddCartItemRequest;
import com.rcr.ecommerce.Services.CartService;
import com.rcr.ecommerce.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    @PostMapping("/add/item-to-cart")
    public ResponseEntity<CartItems> addItemToCart(@RequestHeader("Authorization") String jwt,
                                                   @RequestBody AddCartItemRequest request)throws Exception{
        CartItems item = cartService.addItemToCart(request, jwt);

        return new ResponseEntity<>(item, HttpStatus.CREATED);

    }

    @PutMapping("/update/cart-item/{id}")
    public ResponseEntity<CartItems> updateCartItemQuantity(@RequestHeader("Authorization") String jwt,
                                                   @PathVariable Long id,
                                                            @RequestParam int quantity)throws Exception{
        CartItems item = cartService.updateCartItem(id, quantity);

        return new ResponseEntity<>(item, HttpStatus.CREATED);

    }

    @DeleteMapping("/cart-item/delete/{id}")
    public ResponseEntity<Cart> deleteCartItem(@RequestHeader("Authorization") String jwt,
                                                  @PathVariable Long id)throws Exception{
        Cart cart = cartService.removeItemFromCart(id, jwt);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization") String jwt)
            throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.clearCart(user.getId());

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<Cart> getUserCart(@RequestHeader("Authorization") String jwt)
            throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
