package com.ogifmsim.fmsimulator.dto.login;

public class LoginResponseDTO {
    private String message;
    private String token;

    public LoginResponseDTO(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public String getMessage(){ return this.message; }
    public String getToken() { return this.token; }
}
