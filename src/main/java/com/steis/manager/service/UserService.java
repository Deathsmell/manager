package com.steis.manager.service;

import com.steis.manager.domain.Role;
import com.steis.manager.domain.User;
import com.steis.manager.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

//    @Value("${}")
//    private String sitePath;

    public void correctUser(String username, User user, Map<String, String> form) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key: form.keySet()) {
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
    }

    public boolean saveUser (User user){
        User usernameFromDB = userRepo.findUserByUsername(user.getUsername());
        if (usernameFromDB == null){
            user.setRoles(Collections.singleton(Role.USER));
            userRepo.save(user);
            return true;
        } else return false;
    }

    public Model findAll (Model model){
        Iterable<User> users = userRepo.findAll();
        return model.addAttribute("user", users);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findUserByUsername(username);
    }
}
