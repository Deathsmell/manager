package com.steis.manager.controller;

import com.steis.manager.configuration.JwtTokenUtil;
import com.steis.manager.domain.*;
import com.steis.manager.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class JwtAuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder encoder;
    private final UserRepo userRepo;


    @Autowired
    public JwtAuthenticationController(AuthenticationManager authenticationManager,
                                       JwtTokenUtil jwtTokenUtil,
                                       PasswordEncoder encoder,
                                       UserRepo userRepo) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.encoder = encoder;
        this.userRepo = userRepo;
    }

    @PostMapping(value = "/signin")
    @CrossOrigin(origins = "${cros.access.path}")
    public ResponseEntity<?> authenticateUser (@RequestBody JwtRequest request) throws Exception {

        Optional<User> candidate = userRepo.findByUsername(request.getUsername());

        if (candidate.isPresent()){
            User user = candidate.get();
            Authentication authenticate = authenticate(request.getUsername(), request.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            final String token = jwtTokenUtil.generateToken(user);
            final List<GrantedAuthority> authorities = new ArrayList<>(user.getRoles());
            return ResponseEntity.ok(new JwtResponse(token,request.getUsername(),authorities));
        }

        return ResponseEntity.badRequest().body(new ResponseMessage("Bad sign in application. Pleas try again."));
    }


    @PostMapping(value = "/signup")
    @CrossOrigin(origins = "${cros.access.path}")
    public ResponseEntity<?> singupUser (@RequestBody User user) throws Exception {

        Optional<User> candidate = userRepo.findByUsername(user.getUsername());

        if (candidate.isPresent()){
            return ResponseEntity.badRequest().body(new ResponseMessage("This username exist. Change another name."));
        }

        String password = encoder.encode(user.getPassword());
        User newUser = new User(
                0L,
                user.getUsername(),
                password,
                Set.of(Role.USER)
        );

        userRepo.save(newUser);

        return ResponseEntity.ok(new ResponseMessage("User registration successful"));
    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}