package com.ogifmsim.fmsimulator.dto;

import com.ogifmsim.fmsimulator.model.enums.TacticalFormation;

public class TacticalFormationDTO {
    private final String id;
    private final RoleDTO[] requiredRoles;
    private final int defensiveBonus, offensiveBonus;

    public TacticalFormationDTO(TacticalFormation formation) {
        this.id = formation.getId();
        this.defensiveBonus = formation.getDefensiveBonus();
        this.offensiveBonus = formation.getOffensiveBonus();
        this.requiredRoles = new RoleDTO[11];
        for (int i = 0; i < formation.getRequiredRoles().length; i++) {
            this.requiredRoles[i] = new RoleDTO(formation.getRequiredRoles()[i]);
        }
    }

    public String getId() { return this.id; }
    public RoleDTO[] getRequiredRoles() { return requiredRoles; }
    public int getDefensiveBonus() { return this.defensiveBonus; }
    public int getOffensiveBonus() { return this.offensiveBonus; }
}
