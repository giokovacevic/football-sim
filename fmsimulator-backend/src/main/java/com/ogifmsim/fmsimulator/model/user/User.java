package com.ogifmsim.fmsimulator.model.user;

public class User {
    private String username;
    private String password;
    private UserRole role;

    public User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public UserRole getRole() { return this.role; }
}