package com.rcr.ecommerce.Controllers;

import com.rcr.ecommerce.Configuration.JwtTokenProvider;
import com.rcr.ecommerce.Modal.User;
import com.rcr.ecommerce.Repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository  userRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @GetMapping("/user/jwt")
    public ResponseEntity<User> getUserByJwt(@RequestHeader("Authorization") String jwt)throws Exception{
        String email = jwtTokenProvider.findEmailByJwtToken(jwt);
        User user = userRepository.findByEmail(email);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
