package com.ogifmsim.fmsimulator.dto;

import java.util.HashMap;

import com.ogifmsim.fmsimulator.model.enums.Position;
import com.ogifmsim.fmsimulator.model.enums.Role;

public class RoleDTO {
    private final String stringValue;
    private final int x, y;
    private final PositionDTO position;

    public RoleDTO(Role role) {
        this.stringValue = role.getStringValue();
        this.x = role.getX();
        this.y = role.getY();
        this.position = (role.getPositionValue() != null) ? new PositionDTO(role.getPositionValue()) : null;
    }

    @Override
    public String toString() {
        return this.stringValue;
    }

    public String getStringValue() { return this.stringValue; }
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public PositionDTO getPosition() { return this.position; }
}
