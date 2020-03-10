package com.steis.manager.controller;

import com.steis.manager.domain.User;
import com.steis.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    private final UserService userService;

    @Autowired
    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login (){
        return "login";
    }

    @GetMapping("/registration")
    public String registration (){
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser (User user, Model model){
        if (!userService.saveUser(user)){
            model.addAttribute("massage", "User exists");
            return "registration";
        }
        return "login";
    }


}
