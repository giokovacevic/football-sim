package com.ogifmsim.fmsimulator.dto.login;

public class LoginRequestDTO {
    private String username;
    private String password;

    public LoginRequestDTO() {}

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername(){ return this.username; }
    public String getPassword() { return this.password; }
}
