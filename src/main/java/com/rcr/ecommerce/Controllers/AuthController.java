package com.rcr.ecommerce.Controllers;

import com.rcr.ecommerce.Configuration.JwtTokenProvider;
import com.rcr.ecommerce.Modal.USER_ROLE;
import com.rcr.ecommerce.Modal.User;
import com.rcr.ecommerce.Repository.UserRepository;
import com.rcr.ecommerce.Request.LoginRequest;
import com.rcr.ecommerce.Response.AuthResponse;
import com.rcr.ecommerce.Services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody User user)throws Exception{

        User isExist = userRepository.findByEmail(user.getEmail());
        if(isExist != null){
            throw new Exception("email is already registered");
        }

        User createUser = new User();
        createUser.setEmail(user.getEmail());
        createUser.setName(user.getName());
        createUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createUser.setPhoneNumber(user.getPhoneNumber());
        createUser.setRole(user.getRole());

        User savedUser = userRepository.save(createUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse();
        response.setJwt(jwt);
        response.setMessage("register user successfully");
        response.setRole(savedUser.getRole());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)throws Exception{
        String username = request.getEmail();
        String password = request.getPassword();

        Authentication authentication = authenticate(username, password);

        String jwt = jwtTokenProvider.generateToken(authentication);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        AuthResponse response = new AuthResponse();
        response.setJwt(jwt);
        response.setMessage("Login User Successfully");
        String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
        response.setRole(USER_ROLE.valueOf(role));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        if(userDetails == null){
            throw new BadCredentialsException("username not found");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("invalid password...");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
