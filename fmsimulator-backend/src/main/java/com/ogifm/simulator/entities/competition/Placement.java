package com.ogifm.simulator.entities.competition;

public class Placement {

    private final int competitionId, pot, place;

    public Placement(int place, int competitionId, int pot) {
        this.place = place;
        this.competitionId = competitionId;
        this.pot = pot;
    }

    public int getPlace() {
        return this.place;
    }

    public int getCompetitionId() {
        return this.competitionId;
    }

    public int getPot() {
        return this.pot;
    }
}
