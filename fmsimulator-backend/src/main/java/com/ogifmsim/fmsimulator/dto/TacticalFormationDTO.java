package com.ogifmsim.fmsimulator.dto;

import java.util.HashMap;

import com.ogifmsim.fmsimulator.model.enums.Role;
import com.ogifmsim.fmsimulator.model.enums.TacticalFormation;

public class TacticalFormationDTO {
    private final String id;
    private final Role[] requiredRoles;
    private final int defensiveBonus, offensiveBonus;

    public TacticalFormationDTO(TacticalFormation formation) {
        this.id = formation.getId();
        this.defensiveBonus = formation.getDefensiveBonus();
        this.offensiveBonus = formation.getOffensiveBonus();
        this.requiredRoles = new Role[11];
        for (int i = 0; i < formation.getRequiredRoles().length; i++) {
            this.requiredRoles[i] = formation.getRequiredRoles()[i];
        }
    }

    public String getId() {
        return this.id;
    }

    public RoleDTO[] getRequiredRoleDTOs() {
        RoleDTO[] roleDTOs = new RoleDTO[this.requiredRoles.length];
        for (int i = 0; i < this.requiredRoles.length; i++) {
            roleDTOs[i] = new RoleDTO(this.requiredRoles[i]);
        }

        return roleDTOs;
    }

    public int getDefensiveBonus() {
        return this.defensiveBonus;
    }

    public int getOffensiveBonus() {
        return this.offensiveBonus;
    }

}
