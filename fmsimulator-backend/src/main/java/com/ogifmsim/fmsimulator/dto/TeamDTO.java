package com.ogifmsim.fmsimulator.dto;

import com.ogifmsim.fmsimulator.model.team.Team;

public class TeamDTO {
    private final String type, id, name, fullname, preferredJersey;
    private final CountryDTO country;
    private final RosterDTO roster;

    public TeamDTO(Team team) {
        this.type = team.getType();
        this.id = team.getId();
        this.name = team.getName();
        this.fullname = team.getFullname();
        this.preferredJersey = team.getPreferredJersey();
        this.country = (team.getCountry() != null) ? new CountryDTO(team.getCountry()) : null;
        this.roster = (team.getRoster() != null) ? new RosterDTO(team.getRoster()) : null;
    }

    public String getType() { return this.type; }
    public String getId() { return this.id; }
    public String getName() { return this.name; }
    public String getFullname() { return this.fullname; }
    public String getPreferredJersey() { return this.preferredJersey; }
    public CountryDTO getCountry() { return this.country; }
    public RosterDTO getRoster() { return this.roster; }
}
