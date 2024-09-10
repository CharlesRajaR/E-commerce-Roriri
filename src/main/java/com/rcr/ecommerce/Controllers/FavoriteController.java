package com.rcr.ecommerce.Controllers;

import com.rcr.ecommerce.Modal.ProductDto;
import com.rcr.ecommerce.Modal.User;
import com.rcr.ecommerce.Repository.UserRepository;
import com.rcr.ecommerce.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FavoriteController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @PutMapping("/add-to-fav")
    public ResponseEntity<User> createFavourite(@RequestHeader("Authorization") String jwt,
                                                    @RequestBody ProductDto fav)throws Exception{
        User user= userService.findUserByJwtToken(jwt);

        ProductDto favourite = new ProductDto();
        favourite.setProductId(fav.getProductId());
        favourite.setImages(fav.getImages());
        favourite.setTitle(fav.getTitle());
        favourite.setDescription(fav.getDescription());

        user.getFavourites().add(favourite);
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PutMapping("/remove-to-fav")
    public ResponseEntity<User> deleteFavourite(@RequestHeader("Authorization") String jwt,
                                                    @RequestBody ProductDto fav)throws Exception{
        User user= userService.findUserByJwtToken(jwt);

        user.getFavourites().remove(fav);

        User savedUser = userRepository.save(user);

        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }
}
