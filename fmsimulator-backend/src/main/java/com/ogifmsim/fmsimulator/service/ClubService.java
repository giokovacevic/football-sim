package com.ogifmsim.fmsimulator.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ogifmsim.fmsimulator.model.competition.Competitor;
import com.ogifmsim.fmsimulator.model.competition.league.League;
import com.ogifmsim.fmsimulator.model.enums.TacticalFormation;
import com.ogifmsim.fmsimulator.model.team.Club;

public class ClubService {
    private static ClubService instance = null;
    private static CountryService countryService = CountryService.getInstance();
    private static PlayerService playerService = PlayerService.getInstance();
    private static LeagueService leagueService = LeagueService.getInstance();
    private static List<Club> clubs = null;

    private ClubService() {
         if(clubs == null) clubs = loadAllClubs("db_clubs.csv");
    }

    public static ClubService getInstance() {
        if(instance == null) {
            instance = new ClubService();
        }
        return instance;
    }

    public List<Club> getAllClubs() {
        if(clubs == null) {
            clubs = loadAllClubs("db_clubs.csv");
        }
        return clubs;
    }

    private List<Club> loadAllClubs(String filename) {
        ArrayList<Club> clubList = new ArrayList<>();
        String lastIdLoaded = "";
        
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                String loadedClubString = sc.nextLine();
                String[] clubArray = loadedClubString.split(",");

                if ((countryService.getCountryById(clubArray[7]) == null)) {
                    clubArray[7] = "ALL";
                }
                
                Club clubNew = new Club(clubArray[9], clubArray[2], clubArray[3],
                        countryService.getCountryById(clubArray[7]), clubArray[13],
                        TacticalFormation.generateFormation(clubArray[5]), Integer.parseInt(clubArray[4]), 100.0);

                String leagueId = clubArray[8];

                playerService.getAllPlayers().forEach((p) -> {
                    if (clubNew.getId().equals(p.getContract().getTeamId())) {
                        clubNew.addPlayer(p);
                    }
                });
                
                clubNew.getRoster().getLineup().save();
                lastIdLoaded = clubNew.getId();

                leagueService.getAllLeagues().forEach(league -> {
                    if(league instanceof League) {
                        if(league.getId().equals(leagueId)) {
                            league.getCompetitors().add(new Competitor(clubNew, 0, 0, 0, 0, 0));
                            if(league.getCompetitors().size() == league.getCapacity()) league.createSchedule();
                        }
                    }
                });

                clubList.add(clubNew);
            }

            sc.close();
        } catch (FileNotFoundException | NumberFormatException e) {
            System.out.println(" Error: Club Loader   (Last id loaded:   " + lastIdLoaded + ")");
        }

        return clubList;
    }
}
