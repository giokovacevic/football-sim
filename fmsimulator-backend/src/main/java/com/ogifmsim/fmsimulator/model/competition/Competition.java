package com.ogifmsim.fmsimulator.model.competition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.ogifmsim.fmsimulator.model.team.Team;

public abstract class Competition {

    private final String id;
    private final String name;
    private final String type;

    public Competition(String type, String id, String name) {
        this.type = type;
        this.id = id;
        this.name = name;
    }

    public abstract void print();

    public abstract void createSchedule();
    
    public abstract void simulateRound();

    public abstract boolean isOver();

    public void sort(ArrayList<Competitor> competitors) {

        for (int i = 0; i < (competitors.size() - 1); i++) {
            for (int j = i + 1; j < competitors.size(); j++) {
                if (competitors.get(i).getPoints() < competitors.get(j).getPoints()) {
                    Collections.swap(competitors, i, j);
                } else if (competitors.get(i).getPoints() == competitors.get(j).getPoints()) {
                    if (competitors.get(i).getGoalDifferential() < competitors.get(j).getGoalDifferential()) {
                        Collections.swap(competitors, i, j);
                    } else if (competitors.get(i).getGoalDifferential() == competitors.get(j).getGoalDifferential()) {
                        if (competitors.get(i).getGoalsScored() < competitors.get(j).getGoalsScored()) {
                            Collections.swap(competitors, i, j);
                        }
                    }
                }
            }
        }
    }

    public void simulateRegularMatch(Team home, Team away, Match match) {
        int homeGoals, awayGoals;
        int homeOffense = home.getRoster().getLineup().getOffense();
        int homeDefense = home.getRoster().getLineup().getDefense();
        int awayOffense = away.getRoster().getLineup().getOffense();
        int awayDefense = away.getRoster().getLineup().getDefense();
        if (!match.isNeutral()) {
            homeGoals = generateGoals(homeOffense - awayDefense);
            awayGoals = generateGoals(awayOffense - (homeDefense + 2));
        } else {
            homeGoals = generateGoals(homeOffense - awayDefense);
            awayGoals = generateGoals(awayOffense - (homeDefense));
        }
        match.setScore(homeGoals, awayGoals);
    }

    public void simulateKnockoutMatch(Team home, Team away, Match match) {
        //throw new NotImplementedException();
    }

    public int generateGoals(int offense) {

        if (offense > 40) { // 
            return this.generateRandomNumber(7, 15);
        } else if (offense > 25) { // 
            return this.generateRandomNumber(3, 9);
        } else if (offense > 15) { //
            return this.generateRandomNumber(2, 7);
        } else if (offense > 8) { // 
            return this.generateRandomNumber(1, 5);
        } else if (offense > 3) { // 
            return this.generateRandomNumber(0, 4);
        } else if (offense > 0) { // 
            int goals = this.generateRandomNumber(0, 2);
            return ((Math.random() < 0.5) ? (goals + 1) : goals);
        } else if (offense == 0) { // 
            int goals = this.generateRandomNumber(0, 2);
            return ((Math.random() < 0.2) ? (goals + 1) : goals);
        } else if (offense > (-4)) { // 
            int goals = this.generateRandomNumber(0, 2);
            return ((Math.random() < 0.1) ? (goals + 1) : goals);
        } else if (offense > (-9)) { // 
            return ((Math.random() > 0.5) ? this.generateRandomNumber(0, 2) : this.generateRandomNumber(0, 1));
        } else if (offense > (-16)) { // 
            return ((Math.random() > 0.9) ? this.generateRandomNumber(0, 2) : this.generateRandomNumber(0, 1));
        } else if (offense > (-26)) { // 
            return ((Math.random() > 0.8) ? 1 : 0);
        } else if (offense > (-41)) { // 
            return ((Math.random() > 0.9) ? 1 : 0);
        } else { // YES
            return 0;
        }
    }

    private int generateRandomNumber(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException();
        }
        Random random = new Random();
        return (min + random.nextInt((max - min) + 1));
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }
}
