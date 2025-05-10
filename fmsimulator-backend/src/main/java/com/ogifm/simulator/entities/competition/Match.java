package com.ogifm.simulator.entities.competition;

import com.ogifm.simulator.entities.team.Team;

public class Match {

    private Team host, guest;
    private int score[];
    private final boolean neutral;

    public Match(Team host, Team guest, boolean neutral) {
        this.host = host;
        this.guest = guest;
        this.score = new int[2];
        this.score[0] = 0;
        this.score[1] = 0;
        this.neutral = neutral;
    }

    public boolean isNeutral() {
        return this.neutral;
    }
    public boolean isSet() {
        return !((this.guest == null) || (this.host == null));
    }

    public Team getHost() {
        return this.host;
    }

    public void setHost(Team host) {
        this.host = host;
    }

    public Team getGuest() {
        return this.guest;
    }

    public void setGuest(Team guest) {
        this.guest = guest;
    }

    public void setScore(int[] score) {
        this.score = score;
    }

    public void setScore(int homeGoals, int awayGoals) {
        this.score[0] = homeGoals;
        this.score[1] = awayGoals;
    }

    public int[] getScore() {
        return this.score;
    }
}
