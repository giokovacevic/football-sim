package com.ogifmsim.fmsimulator.model.competition.league;

import java.util.ArrayList;

import com.ogifmsim.fmsimulator.model.competition.Competitor;
import com.ogifmsim.fmsimulator.model.competition.Match;
import com.ogifmsim.fmsimulator.model.competition.Schedule;
import com.ogifmsim.fmsimulator.model.competition.ScheduleGenerator;

public class LeagueOf10 extends League{

    public LeagueOf10(String id, String name, int cicle, int round, String parentLeagueId, int relegation, Schedule schedule) {
        super(id, name, cicle, round, parentLeagueId, relegation, schedule);
        this.competitors = new ArrayList<>(10);
    }

    @Override
    public void createSchedule() {
        this.schedule = new Schedule();
        for(int r=0; r<getLastRound(); r++) {
            ArrayList<Match> matches = new ArrayList<>(); 
            for(int i=0; i<(getCapacity()/2); i++) {
                matches.add(new Match(null, null, true));
            }
            this.schedule.getMatches().put(r, matches);
        }

        ScheduleGenerator sg = new ScheduleGenerator(getTeams());
        this.schedule.fill(0, sg.getSchedule());
        sg.invert();
        this.schedule.fill(getCapacity()-1, sg.getSchedule());
    }
    
    @Override
    public void simulateRound() {
        if (!this.isOver()) {
            for (Match match : this.schedule.getMatches().get(this.round - 1)) {
                Competitor homeTeam = getCompetitorById(match.getHost().getId());
                Competitor awayTeam = getCompetitorById(match.getGuest().getId());
                simulateRegularMatch(homeTeam.getTeam(), awayTeam.getTeam(), match);
                homeTeam.updateResults(match.getScore()[0], match.getScore()[1]);
                awayTeam.updateResults(match.getScore()[1], match.getScore()[0]);
                //System.out.println(" round simulated succesfully" + this.round);
            }

            sort(this.competitors);
            this.round++;
            if (isCicleOver()) {
                ScheduleGenerator sg = new ScheduleGenerator(getTeams());
                sg.sort(getTeams());
                this.schedule.fill((getCapacity()-1)*2, sg.getSchedule());
                this.cicle++;
            }
        }
    }

    @Override
    public boolean isCicleOver() {
        return this.round == (((getCapacity() - 1) * 2) + 1);
    }

    @Override
    public int getLastRound() {
        return ((getCapacity() - 1) * 3);

    }

    @Override
    public int getCapacity() {
        return 10;
    }
}