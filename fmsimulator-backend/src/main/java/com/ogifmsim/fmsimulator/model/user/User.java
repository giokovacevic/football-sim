package com.ogifmsim.fmsimulator.model.user;

public class User {
    private String username;
    private UserRole role;

    public User(String username, UserRole role) {
        this.username = username;
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getUsername() { return this.username; }
    public UserRole getRole() { return this.role; }
}