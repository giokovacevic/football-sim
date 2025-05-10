package com.ogifm.simulator.entities.competition.league;


import java.util.ArrayList;
import java.util.HashMap;

import com.ogifm.simulator.entities.competition.Competition;
import com.ogifm.simulator.entities.competition.Competitor;
import com.ogifm.simulator.entities.competition.Placement;
import com.ogifm.simulator.entities.competition.Schedule;
import com.ogifm.simulator.entities.team.Team;

public class League extends Competition {

    protected int round, cicle, relegation;
    protected String parentLeagueId;
    protected ArrayList<Competitor> competitors;
    protected Schedule schedule;
    protected HashMap<Integer, Placement> placements;

    public League(String id, String name, int cicle, int round, String parentLeagueId, int relegation, Schedule schedule) {
        super("League", id, name);
        this.cicle = cicle;
        this.round = round;
        this.parentLeagueId = parentLeagueId;
        this.relegation = relegation;
        this.competitors = null;
        this.placements = new HashMap<>();
        this.schedule = schedule;
    }

    @Override
    public void print() {
        System.out.printf("\n %s     Cicle: %d     Round:  %d", getName(), cicle, round);
        System.out.printf("\n=========================================================================");
        System.out.printf("\n %3s | %20s  | %3s | %3s | %3s |  %3s  |  %3s  |  %4s  |", "Pos", "Name", "W", "D", "L", "GS", "GA", "P");
        System.out.printf("\n-------------------------------------------------------------------------");
        for (int i = 1; i <= this.competitors.size(); i++) {
            System.out.printf("\n %3d | ", i);
            this.competitors.get(i - 1).print();
            System.out.printf("\n-------------------------------------------------------------------------");
        }
    }

    @Override
    public void createSchedule() {
        System.out.println("Empty Schedule");
    }

    @Override
    public void simulateRound() {
        System.out.println(" League Round Simulated. ");

    }

    @Override
    public boolean isOver() {
        return this.round > getLastRound();
    }

    public boolean isCicleOver() {
        return true;
    }

    public int getLastRound() {
        return 0;

    }

    public int getCapacity() {
        return 0;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setCicle(int cicle) {
        this.cicle = cicle;
    }

    public void setRelegation(int relegation) {
        this.relegation = relegation;
    }

    public void setPlacements(HashMap<Integer, Placement> placements) {
        this.placements = placements;
    }

    public int getRound() {
        return this.round;
    }

    public int getCicle() {
        return this.cicle;
    }

    public int getRelegation() {
        return this.relegation;
    }

    public String getParentLeagueId() {
        return this.parentLeagueId;
    }

    public HashMap<Integer, Placement> getPlacements() {
        return this.placements;
    }

    public Competitor getCompetitorById(String id) {
        for (Competitor c : this.competitors) {
            if (c.getTeam().getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Competitor> getCompetitors() {
        return this.competitors;
    }
    public Team[] getTeams() {
        Team[] teams = new Team[this.competitors.size()];
        int i=0;
        for(Competitor competitor: this.competitors) {
            teams[i] = competitor.getTeam();
            i++;
        }
        return teams;
    }

    public Schedule getSchedule() {
        return this.schedule;
    }

}
