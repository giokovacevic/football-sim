package com.ogifmsim.fmsimulator.dto;

import com.ogifmsim.fmsimulator.model.enums.Role;
import com.ogifmsim.fmsimulator.model.player.Arrangement;

public class ArrangementDTO {
    private final String teamId, teamName;
    private final int jerseyNumber;
    private final RoleDTO role;

    public ArrangementDTO(Arrangement arrangement) {
        this.teamId = (arrangement.getTeam() == null) ? "default" : arrangement.getTeam().getId();
        this.teamName = (arrangement.getTeam() == null) ? "Free Agent" : arrangement.getTeam().getName();
        this.jerseyNumber = arrangement.getJerseyNumber();
        this.role = (arrangement.getRole() == null) ? new RoleDTO(Role.BENCH) : new RoleDTO(arrangement.getRole());
    }

    public String getTeamId() { return this.teamId; }
    public String getTeamName() { return this.teamName; }
    public int getJerseyNumber() { return this.jerseyNumber; }
    public RoleDTO getRole() { return this.role; }
}
