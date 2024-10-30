package com.blitz.security.service;

import com.blitz.security.model.Users;
import com.blitz.security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public Users registerUser(Users user){
        user.setPass(encoder.encode(user.getPass()));
        return repo.save(user);
    }

}
