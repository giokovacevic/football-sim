package com.ogifm.simulator.entities.player;

import com.ogifm.simulator.dto.RoleDTO;
import com.ogifm.simulator.enums.Role;

public class Arrangement {

    protected String teamId;
    protected int jerseyNumber;
    protected Role role;

    public Arrangement(String teamId, int jerseyNumber, Role role) {
        this.teamId = teamId;
        this.jerseyNumber = jerseyNumber;
        this.role = role;
    }

    public Arrangement(Arrangement copy) {
        this.teamId = copy.getTeamId();
        this.jerseyNumber = copy.getJerseyNumber();
        this.role = copy.getRole();
    }

    public boolean isStarting() {
        return ((!(this.role.getStringValue().equals(Role.BENCH.getStringValue())))
                && (!this.role.getStringValue().equals(Role.UD.getStringValue())));
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getTeamId() {
        return this.teamId;
    }

    public int getJerseyNumber() {
        return this.jerseyNumber;
    }

    public Role getRole() {
        return this.role;
    }

    public RoleDTO getRoleDTO() {
        return new RoleDTO(this.role);
    }
}
