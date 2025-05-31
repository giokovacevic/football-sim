package com.ogifmsim.fmsimulator.dto.user;

import com.ogifmsim.fmsimulator.model.user.UserRole;

public class UserRoleDTO {
    private final String name, url;
    
    public UserRoleDTO(UserRole role) {
        this.name = role.getName();
        this.url = role.getUrl();
    }

    public String getName() { return this.name; }
    public String getUrl() { return this.url; }
}
