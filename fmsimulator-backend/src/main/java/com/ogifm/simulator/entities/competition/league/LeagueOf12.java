package com.ogifm.simulator.entities.competition.league;

import java.util.ArrayList;

import com.ogifm.simulator.entities.competition.Competitor;
import com.ogifm.simulator.entities.competition.Match;
import com.ogifm.simulator.entities.competition.Schedule;
import com.ogifm.simulator.entities.team.Team;
import com.ogifm.simulator.util.ScheduleGenerator;

public class LeagueOf12 extends League {

    public LeagueOf12(String id, String name, int cicle, int round, String parentLeagueId, int relegation, Schedule schedule) {
        super(id, name, cicle, round, parentLeagueId, relegation, schedule);
        this.competitors = new ArrayList<>(12);
        //schedule
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
                Team[] half1teams = new Team[6];
                half1teams[0] = this.competitors.get(0).getTeam();
                half1teams[1] = this.competitors.get(3).getTeam();
                half1teams[2] = this.competitors.get(5).getTeam();
                half1teams[3] = this.competitors.get(7).getTeam();
                half1teams[4] = this.competitors.get(9).getTeam();
                half1teams[5] = this.competitors.get(11).getTeam();
                ScheduleGenerator sg = new ScheduleGenerator(half1teams);
                sg.sort(half1teams);
                this.schedule.fill((getCapacity()-1)*2, sg.getSchedule());

                Team[] half2teams = new Team[6];
                half2teams[0] = this.competitors.get(1).getTeam();
                half2teams[1] = this.competitors.get(2).getTeam();
                half2teams[2] = this.competitors.get(4).getTeam();
                half2teams[3] = this.competitors.get(6).getTeam();
                half2teams[4] = this.competitors.get(8).getTeam();
                half2teams[5] = this.competitors.get(10).getTeam();
                sg = new ScheduleGenerator(half2teams);
                sg.sort(half2teams);
                this.schedule.fill((getCapacity()-1)*2, sg.getSchedule(), ((getCapacity()/2)/2));

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
        return (((getCapacity() - 1) * 2) + (getCapacity()/2 - 1));

    }

    @Override
    public int getCapacity() {
        return 12;
    }
}

