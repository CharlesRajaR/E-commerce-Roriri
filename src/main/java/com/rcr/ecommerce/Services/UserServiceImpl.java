package com.rcr.ecommerce.Services;

import com.rcr.ecommerce.Configuration.JwtTokenProvider;
import com.rcr.ecommerce.Modal.User;
import com.rcr.ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public User uploadProfilePicture(String image, Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new Exception("user not found");
        }
        User currentUser = user.get();
        currentUser.setProfilePicture(image);

        return userRepository.save(currentUser);
    }


}
