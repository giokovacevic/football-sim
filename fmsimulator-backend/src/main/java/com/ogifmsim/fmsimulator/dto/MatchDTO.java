package com.ogifmsim.fmsimulator.dto;

import com.ogifmsim.fmsimulator.model.competition.Match;
import com.ogifmsim.fmsimulator.model.team.Team;

public class MatchDTO {
    private final String hostId, hostName, guestId, guestName;
    private final int score[];
    private final boolean neutrality;

    public MatchDTO(Match match) {
        this.hostId = (match.getHost() != null) ? match.getHost().getId() : "";
        this.hostName = (match.getHost() != null) ? match.getHost().getName() : "";
        this.guestId = (match.getGuest() != null) ? match.getGuest().getId() : "";
        this.guestName = (match.getGuest() != null) ? match.getGuest().getName() : "";
        this.score = match.getScore();
        this.neutrality = match.isNeutral();
    }

    public String getHostId() { return this.hostId; }
    public String getHostName() { return this.hostName; }
    public String getGuestId() { return this.guestId; }
    public String getGuestName() { return this.guestName; }
    public int[] getScore() { return this.score; }
    public boolean getNeutrality() { return this.neutrality; }
}
