package com.ogifmsim.fmsimulator.dto;

import com.ogifmsim.fmsimulator.model.competition.Competitor;

public class CompetitorDTO {
    private final TeamDTO team;
    private final int wins, draws, losses, goalsScored, goalsConceded, points;
    public CompetitorDTO(Competitor competitor) {
        this.team = (competitor.getTeam() != null) ? new TeamDTO(competitor.getTeam()) : null;
        this.wins = competitor.getWins();
        this.draws = competitor.getDraws();
        this.losses = competitor.getLosses();
        this.goalsScored = competitor.getGoalsScored();
        this.goalsConceded = competitor.getGoalsConceded();
        this.points = competitor.getPoints();
    }

    public TeamDTO getTeam() { return this.team; }
    public int getWins() { return this.wins; }
    public int getDraws() { return this.draws; }
    public int getLosses() { return this.losses; }
    public int getGoalsScored() { return this.goalsScored; }
    public int getGoalsConceded() { return this.goalsConceded; }
    public int getPoints() { return this.points; }
}
