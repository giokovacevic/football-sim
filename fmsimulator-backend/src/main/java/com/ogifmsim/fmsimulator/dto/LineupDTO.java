package com.ogifmsim.fmsimulator.dto;

import java.util.ArrayList;

import com.ogifmsim.fmsimulator.model.enums.Role;
import com.ogifmsim.fmsimulator.model.team.Lineup;

public class LineupDTO {
    private final TacticalFormationDTO formation;
    private final boolean viability;
    private final int offense, defense;
    private final ArrayList<StarterDTO> starters;

    public LineupDTO(Lineup lineup){
        this.viability = lineup.isViable();
        this.offense = lineup.getOffense();
        this.defense = lineup.getDefense();
        this.starters = new ArrayList<>();
        this.formation = (lineup.getFormation() != null) ? new TacticalFormationDTO(lineup.getFormation()) : null;
        if(lineup.getFormation() != null) {
            for(Role role: lineup.getFormation().getRequiredRoles()) {
                this.starters.add(new StarterDTO(lineup.getStarters().get(role), role));
            }
        }
    }

    public TacticalFormationDTO getFormation() { return this.formation; }
    public boolean getViability() { return this.viability; }
    public int getDefense() { return this.defense; }
    public int getOffense() { return this.offense; }
    public ArrayList<StarterDTO> getStarters() { return this.starters; }
}
