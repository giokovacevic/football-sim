package com.ogifmsim.fmsimulator.dto.login;

import com.ogifmsim.fmsimulator.dto.user.UserDTO;
import com.ogifmsim.fmsimulator.model.user.User;

public class LoginResponseDTO {
    private final String message;
    private final String token;
    private final UserDTO user;

    public LoginResponseDTO(String message, String token, User user) {
        this.message = message;
        this.token = token;
        this.user = (user != null) ? new UserDTO(user) : null;
    }

    public String getMessage(){ return this.message; }
    public String getToken() { return this.token; }
    public UserDTO getUser() { return this.user; }
}
