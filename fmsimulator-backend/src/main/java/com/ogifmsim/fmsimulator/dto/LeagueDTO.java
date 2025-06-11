package com.ogifmsim.fmsimulator.dto;

import java.util.ArrayList;

import com.ogifmsim.fmsimulator.model.competition.Competitor;
import com.ogifmsim.fmsimulator.model.competition.Match;
import com.ogifmsim.fmsimulator.model.competition.league.League;

public class LeagueDTO extends CompetitionDTO{
    private final int round, cycle, relegation;
    private final String parentLeagueId/*, parentLeagueName*/; // TODO: Parent league name 
    private final ArrayList<CompetitorDTO> competitors;
    private final ArrayList<ArrayList<MatchDTO>> schedule;


    public LeagueDTO(League league) {
        super(league);
        this.round = league.getRound();
        this.cycle = league.getCycle();
        this.relegation = league.getRelegation();
        this.parentLeagueId = league.getParentLeagueId();
        this.competitors = new ArrayList<>();
        for(Competitor competitor : league.getCompetitors()) {
            this.competitors.add(new CompetitorDTO(competitor));
        }
        this.schedule = (league.getSchedule() != null) ? new ArrayList<>() : null;
        for(Integer i : league.getSchedule().getMatches().keySet()){
            this.schedule.add(new ArrayList<MatchDTO>());
            for(Match match : league.getSchedule().getMatches().get(i)) {
                this.schedule.get(i).add(new MatchDTO(match));
            }
        }
    }

    public int getRound() { return this.round;}
    public int getCycle() { return this.cycle;}
    public int getRelegation() { return this.relegation;}
    public String getParentLeagueId() { return this.parentLeagueId; }
    public ArrayList<CompetitorDTO> getCompetitors(){ return this.competitors; }
    public ArrayList<ArrayList<MatchDTO>> getSchedule() { return this.schedule; }
}
