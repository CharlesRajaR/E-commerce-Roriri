package com.rcr.ecommerce.Services;

import com.rcr.ecommerce.Configuration.JwtTokenProvider;
import com.rcr.ecommerce.Modal.User;
import com.rcr.ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;
    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtTokenProvider.findEmailByJwtToken(jwt);
        User user = findByEmail(email);
        return user;
    }

    @Override
    public User findByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new Exception("user not found with this email");
        }
        return user;
    }
}
