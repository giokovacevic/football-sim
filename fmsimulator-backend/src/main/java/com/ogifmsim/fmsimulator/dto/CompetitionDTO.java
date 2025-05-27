package com.ogifmsim.fmsimulator.dto;

import com.ogifmsim.fmsimulator.model.competition.Competition;

public class CompetitionDTO {
    private final String id, name, type;
    
    public CompetitionDTO(Competition competition) {
        this.id = competition.getId();
        this.name = competition.getName();
        this.type = competition.getType();
    }

    public String getId(){ return this.id; }
    public String getName(){ return this.name; }
    public String getType(){ return this.type; }
}
