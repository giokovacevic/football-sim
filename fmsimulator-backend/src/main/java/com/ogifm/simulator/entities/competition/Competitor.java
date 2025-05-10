package com.ogifm.simulator.entities.competition;

import com.ogifm.simulator.entities.team.Team;


public class Competitor {

    private Team team;
    private int wins, draws, losses, goalsScored, goalsConceded;

    public Competitor(Team team, int wins, int draws, int losses, int goalsScored, int goalsConceded) {
        this.team = team;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.goalsConceded = goalsConceded;
        this.goalsConceded = goalsConceded;
    }

    public void print() {
        System.out.printf("%20s  | %3d | %3d | %3d |  %3d  |  %3d  |  %4d  |", team.getName(), wins, draws, losses, goalsScored, goalsConceded, getPoints());
    }

    public void updateResults(int goalsScored, int goalsConceded) {
        if (goalsScored > goalsConceded) {
            this.wins++;
        } else if (goalsScored < goalsConceded) {
            this.losses++;
        } else {
            this.draws++;
        }

        this.goalsScored += goalsScored;
        this.goalsConceded += goalsConceded;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    public int getPoints() {
        return ((this.wins * 3) + (this.draws * 1));
    }

    public int getGoalDifferential() {
        return this.goalsScored - this.goalsConceded;
    }

    public Team getTeam() {
        return this.team;
    }

    public int getWins() {
        return this.wins;
    }

    public int getDraws() {
        return this.draws;
    }

    public int getLosses() {
        return this.losses;
    }

    public int getGoalsScored() {
        return this.goalsScored;
    }

    public int getGoalsConceded() {
        return this.goalsConceded;
    }

}
