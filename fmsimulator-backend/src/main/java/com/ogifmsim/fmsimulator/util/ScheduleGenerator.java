package com.ogifmsim.fmsimulator.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.ogifmsim.fmsimulator.model.competition.Match;
import com.ogifmsim.fmsimulator.model.team.Team;

public final class ScheduleGenerator {

    private HashMap<Integer, ArrayList<Match>> schedule;
    private int rounds, matches;

    /**
     * Generate a single cicle, round robin schedule.
     *
     * @param teams teams of all teams in a same League.
     */
    public ScheduleGenerator(Team[] teams) {
        this.rounds = (teams.length - 1);
        this.matches = teams.length / 2;
        this.schedule = new HashMap<>();

        for (int r = 0; r < this.rounds; r++) {
            this.schedule.put(r, new ArrayList<>());
            for (int m = 0; m < this.matches; m++) {
                this.schedule.get(r).add(new Match(null, null, false));
            }
        }

        for (int r = 0; r < this.rounds; r++) {
            this.generateRound(teams, r);
            this.rotate(teams);
        }
    }

    private void generateRound(Team[] teams, int i) {
        int last = teams.length;
        if (Math.random() < 0.5) {
            this.schedule.get(i).get(0).setHost(teams[0]);
            this.schedule.get(i).get(0).setGuest(teams[last - 1]);
        } else {
            this.schedule.get(i).get(0).setHost(teams[last - 1]);
            this.schedule.get(i).get(0).setGuest(teams[0]);
        }

        int index = 1;
        int k = last - 2;
        int j = 1;
        while (j < k) {
            this.schedule.get(i).get(index).setHost(teams[k]);
            this.schedule.get(i).get(index).setGuest(teams[j]);
            k = k - 1;
            j = j + 1;
            index = index + 1;
        }
    }

    private void rotate(Team[] teams) {
        int size = teams.length;
        Team x = teams[0];
        for (int i = 0; i < (size - 2); i++) {
            teams[i] = teams[i + 1];
        }
        teams[size - 2] = x;
    }

    public void invert() {
        this.schedule.forEach((key, value) -> {
            value.forEach((match) -> {
                Team temp = match.getHost();
                match.setHost(match.getGuest());
                match.setGuest(temp);
            });
        });
    }

    public void sort(Team[] order) { // NOT DONE
        this.schedule.forEach((key, value) -> {
            value.forEach((match) -> {
                Team host = match.getHost();
                Team guest = match.getGuest();
                int positionHost = -1;
                int positionGuest = -1;
                int i= 0;
                while(positionHost == (-1)) {
                    if(host.getId().equals(order[i].getId())) {
                        positionHost = i;
                    }
                    i++;
                }
                i=0;
                while(positionGuest == (-1)) {
                    if(guest.getId().equals(order[i].getId())) {
                        positionGuest = i;
                    }
                    i++;
                }
                if(positionGuest < positionHost) {
                    Team temp = match.getHost();
                    match.setHost(match.getGuest());
                    match.setGuest(temp);
                }
            });
        });
    }

    public void print() {
        this.schedule.forEach((key, value) -> {
            System.out.println("");
            value.forEach((match) -> {
                System.out.printf(" %30s ", (match.getHost().getName() + "   :   " + match.getGuest().getName()));
            });
        });
    }

    public HashMap<Integer, ArrayList<Match>> getSchedule() {
        return this.schedule;
    }

    public void setSchedule(HashMap<Integer, ArrayList<Match>> schedule) {
        this.schedule = schedule;
    }
}


