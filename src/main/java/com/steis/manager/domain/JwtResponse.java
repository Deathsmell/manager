package com.steis.manager.domain;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final List<? extends GrantedAuthority> authorities;

    public String getUsername() {
        return username;
    }

    private final String username;

    public JwtResponse(String jwttoken, String username, List<? extends GrantedAuthority> authorities) {
        this.jwttoken = jwttoken;
        this.username = username;
        this.authorities = authorities;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
