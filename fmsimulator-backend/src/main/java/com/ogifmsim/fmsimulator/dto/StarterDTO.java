package com.ogifmsim.fmsimulator.dto;

import com.ogifmsim.fmsimulator.model.enums.Role;
import com.ogifmsim.fmsimulator.model.team.Starter;

public class StarterDTO {
    private final PlayerDTO player;
    private final int currentRating;
    private final boolean wrongPosition;
    private final RoleDTO requiredRole;

    public StarterDTO(Starter starter, Role role) {
        this.player = (starter.getPlayer() != null) ? new PlayerDTO(starter.getPlayer()) : null;
        this.currentRating = starter.getCurrentRating();
        this.wrongPosition = starter.getOutOfPosition();
        this.requiredRole = new RoleDTO(role);
    }

    public PlayerDTO getPlayer() { return this.player; }
    public int getCurrentRating() { return this.currentRating; }
    public boolean getWrongPosition() { return this.wrongPosition; }
    public RoleDTO getRequiredRole() { return this.requiredRole; }
}
