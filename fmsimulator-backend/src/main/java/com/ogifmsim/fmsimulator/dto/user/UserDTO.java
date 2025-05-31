package com.ogifmsim.fmsimulator.dto.user;

import com.ogifmsim.fmsimulator.dto.RoleDTO;
import com.ogifmsim.fmsimulator.model.user.User;
import com.ogifmsim.fmsimulator.model.user.UserRole;

public class UserDTO {
    private final String username;
    private final UserRoleDTO role;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.role = (user.getRole() != null) ? new UserRoleDTO(user.getRole()) : null;
    }

    public String getUsername() { return this.username; }
    public UserRoleDTO getRole() { return this.role; }
}
