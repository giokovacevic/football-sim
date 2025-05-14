package com.ogifmsim.fmsimulator.model.competition;

import java.util.ArrayList;
import java.util.HashMap;

public class Schedule {
    HashMap<Integer, ArrayList<Match>> matches;

    public Schedule() {
        this.matches = new HashMap<>();
    }

    public void print() {
        this.matches.forEach((key, item) -> {
            item.forEach((match) -> {
                if(match.isSet()) {
                    System.out.println(match.getHost().getName() + " : " + match.getGuest().getName());
                }
            });
            System.out.println();
        });
    }

    public void fill(int round, HashMap<Integer, ArrayList<Match>> schedule) {
        for(int r=0; r<schedule.size(); r++) {
            for(int m=0; m<schedule.get(r).size(); m++) {
                Match match = schedule.get(r).get(m);
                this.matches.get(round+r).set(m, new Match(match.getHost(), match.getGuest(), match.isNeutral()));
            }
        }
    }
    public void fill(int round, HashMap<Integer, ArrayList<Match>> schedule, int matchStart) {
        for(int r=0; r<schedule.size(); r++) {
            for(int m=0; m<schedule.get(r).size(); m++) {
                Match match = schedule.get(r).get(m);
                this.matches.get(round+r).set(m+matchStart, new Match(match.getHost(), match.getGuest(), match.isNeutral()));
            }
        }
    }

    public HashMap<Integer, ArrayList<Match>> getMatches() {
        return this.matches;
    }
}
