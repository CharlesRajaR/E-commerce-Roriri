package com.rcr.ecommerce.Services;

import com.rcr.ecommerce.Modal.Cart;
import com.rcr.ecommerce.Modal.CartItems;
import com.rcr.ecommerce.Request.AddCartItemRequest;

public interface CartService {
    public CartItems addItemToCart(AddCartItemRequest request, String jwt)throws Exception;
    public CartItems updateCartItem(Long cartItemId, int quantity)throws Exception;
    public Cart removeItemFromCart(Long cartItemId, String jwt)throws Exception;

    public Long calculateCartTotals(Cart cart)throws Exception;
    public Cart findCartById(Long cartId)throws Exception;
    public Cart findCartByUserId(Long userId)throws Exception;
    public Cart clearCart(Long userId)throws Exception;

}
