package com.ogifm.simulator.game;

import java.util.ArrayList;
import java.util.HashMap;

import com.ogifm.simulator.entities.competition.Competition;
import com.ogifm.simulator.entities.competition.league.League;
import com.ogifm.simulator.entities.country.Country;
import com.ogifm.simulator.entities.player.Player;


public class World{
    public static int YEAR;
    private int day;
    private HashMap<String, Country> countries;
    private int[] calendar;
    private ArrayList<Competition> competitions;
    private ArrayList<Player> freeAgents;

    private ArrayList<League> leagues; // temporary for leagues only

    public World(int year, String calendar, int day) {
        YEAR = year;
        setCalendar(calendar);
        this.day = day;
        this.countries = new HashMap<>();
        this.competitions = new ArrayList<>();
        this.freeAgents = new ArrayList<>();

        this.leagues = new ArrayList<>();
    }


    public boolean isMatchDay() {
        return ((this.calendar[day-1] == 1) || (this.calendar[day-1] == 2) || (this.calendar[day-1] == 3) || (this.calendar[day-1] == 4));
    }

    public void setYear(int year) {
        YEAR = year;
    }
    public void setCalendar(String calendarString) {
        this.calendar = null;
        if(calendarString!=null) {
            String[] calendarStringValues = calendarString.split(" ");
            this.calendar = new int[calendarStringValues.length];
        for(int i=0; i<calendarStringValues.length; i++) {  
            this.calendar[i] = Integer.parseInt(calendarStringValues[i]);
        }
        }else{
            this.calendar = null;
        }

    }
    public void setDay(int day) {
        this.day = day;
    }
    public void setCountries(HashMap<String, Country> countries) {
        this.countries = countries;
    }
    public void setFreeAgents(ArrayList<Player> freeAgents) {
        this.freeAgents = freeAgents;
    }

    public int getDay() {
        return this.day;
    }
    public int[] getCalendar() {
        return this.calendar;
    }
    public HashMap<String, Country> getCountries() {
        return this.countries;
    }
    public ArrayList<Competition> getCompetitions() {
        return this.competitions;
    }
    public ArrayList<Player> getFreeAgents() {
        return this.freeAgents;
    }


    //temporary
    public void setLeagues(ArrayList<League> leagues) {
        this.leagues = leagues;
    }
    public ArrayList<League> getLeagues() {
        return this.leagues;
    }
}
