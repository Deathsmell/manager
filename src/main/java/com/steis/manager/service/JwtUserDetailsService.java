package com.steis.manager.service;

import com.steis.manager.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public JwtUserDetailsService(UserRepo userRepo, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if ("secret".equals(username)) {
            return new User(
                    "javainuse",
                    "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>()
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

    }

}
