package com.ogifmsim.fmsimulator.model.player;

import com.ogifmsim.fmsimulator.dto.RoleDTO;
import com.ogifmsim.fmsimulator.model.enums.Role;
import com.ogifmsim.fmsimulator.model.team.Team;

public class Arrangement {

    protected Team team;
    protected int jerseyNumber;
    protected Role role;

    public Arrangement(Team team, int jerseyNumber, Role role) {
        this.team = team;
        this.jerseyNumber = jerseyNumber;
        this.role = role;
    }

    public Arrangement(Arrangement copy) {
        this.team = copy.getTeam();
        this.jerseyNumber = copy.getJerseyNumber();
        this.role = copy.getRole();
    }

    public boolean isStarting() {
        return ((!(this.role.getStringValue().equals(Role.BENCH.getStringValue())))
                && (!this.role.getStringValue().equals(Role.UD.getStringValue())));
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Team getTeam() {
        return this.team;
    }

    public int getJerseyNumber() {
        return this.jerseyNumber;
    }

    public Role getRole() {
        return this.role;
    }
}
