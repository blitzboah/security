package com.blitz.security.service;

import com.blitz.security.model.Users;
import com.blitz.security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepo repo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public Users registerUser(Users user){
        user.setPass(encoder.encode(user.getPass()));
        return repo.save(user);
    }

    public String verifyUser(Users user){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(), user.getPass()
                    ));
            if(authentication.isAuthenticated()){
                return jwtService.generateToken(user.getUsername());
            }
        }
       catch (BadCredentialsException e) {
           throw new BadCredentialsException("invalid");
       }
        return "failed";
    }

}
