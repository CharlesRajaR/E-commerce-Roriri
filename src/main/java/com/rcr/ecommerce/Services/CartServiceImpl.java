package com.rcr.ecommerce.Services;

import com.rcr.ecommerce.Configuration.JwtTokenProvider;
import com.rcr.ecommerce.Modal.Cart;
import com.rcr.ecommerce.Modal.CartItems;
import com.rcr.ecommerce.Modal.Product;
import com.rcr.ecommerce.Modal.User;
import com.rcr.ecommerce.Repository.CartItemRepository;
import com.rcr.ecommerce.Repository.CartRepository;
import com.rcr.ecommerce.Repository.ProductRepository;
import com.rcr.ecommerce.Repository.UserRepository;
import com.rcr.ecommerce.Request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public CartItems addItemToCart(AddCartItemRequest request, String jwt) throws Exception {
        String email = jwtTokenProvider.findEmailByJwtToken(jwt);
        User user = userRepository.findByEmail(email);
        Cart cart = cartRepository.findByCustomerId(user.getId());
        Product product = productService.findProduct(request.getProductId());

        for(CartItems item: cart.getItems()){
            if(item.getProduct().equals(product)){
                int newQuantity = item.getQuantity()+request.getQuantity();
                return updateCartItem(item.getId(), newQuantity);
            }
        }
        CartItems item = new CartItems();
        item.setProduct(product);
        item.setQuantity(request.getQuantity());
        item.setPrice(product.getPrice()*request.getQuantity());
        item.setCart(cart);

        CartItems savedCartItem = cartItemRepository.save(item);
        cart.getItems().add(savedCartItem);

        return savedCartItem;
    }

    @Override
    public CartItems updateCartItem(Long cartItemId, int quantity) throws Exception {
        Optional<CartItems> item = cartItemRepository.findById(cartItemId);
        if(item.isEmpty()){
            throw new Exception("cart item not found");
        }
        CartItems item1 = item.get();
        if(item1.getProduct().getQuantity() >= quantity){
            item1.setQuantity(quantity);
            item1.setPrice(item1.getProduct().getPrice()*quantity);
        }
        else {
            throw new Exception("your quantity is greater than the available quantity");
        }
        return cartItemRepository.save(item1);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        String email = jwtTokenProvider.findEmailByJwtToken(jwt);
        User user = userRepository.findByEmail(email);
        Cart cart = cartRepository.findByCustomerId(user.getId());
        Optional<CartItems> item = cartItemRepository.findById(cartItemId);
        if(item.isEmpty()){
            throw new Exception("cart item was not found");
        }
        CartItems item1 = item.get();
        cart.getItems().remove(item1);

        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        Long total = 0L;
        for(CartItems item: cart.getItems()){
            total += item.getProduct().getPrice()* item.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long cartId) throws Exception {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if (cart.isEmpty()){
            throw new Exception("cart not found");
        }
        return cart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        Cart cart = cartRepository.findByCustomerId(userId);
        cart.setTotalPrice(calculateCartTotals(cart));
        return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart = cartRepository.findByCustomerId(userId);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
