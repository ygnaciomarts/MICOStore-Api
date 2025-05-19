package org.mico.micostoreapi.auth;

import org.mico.micostoreapi.dto.UserDTO;

public class JwtResponse {
    private String token;
    private UserDTO user;

    public JwtResponse(String token, UserDTO user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public UserDTO getUser() {
        return user;
    }
}