package com.rcr.ecommerce.Controllers;

import com.rcr.ecommerce.Configuration.JwtTokenProvider;
import com.rcr.ecommerce.Modal.User;
import com.rcr.ecommerce.Repository.UserRepository;
import com.rcr.ecommerce.Request.ProfileRequest;
import com.rcr.ecommerce.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository  userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @GetMapping("/user/jwt")
    public ResponseEntity<User> getUserByJwt(@RequestHeader("Authorization") String jwt)throws Exception{
        String email = jwtTokenProvider.findEmailByJwtToken(jwt);
        User user = userRepository.findByEmail(email);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/upload/profile")
    public ResponseEntity<User> uploadProfile(@RequestHeader("Authorization") String jwt,
                                              @RequestBody ProfileRequest req)throws Exception{
        String email = jwtTokenProvider.findEmailByJwtToken(jwt);
        User user = userRepository.findByEmail(email);

        User profileUploaded = userService.uploadProfilePicture(req.getImage(), user.getId());

        return new ResponseEntity<>(profileUploaded, HttpStatus.OK);
    }

}
