package com.ogifmsim.fmsimulator.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ogifmsim.fmsimulator.dto.LeagueDTO;
import com.ogifmsim.fmsimulator.model.competition.league.League;
import com.ogifmsim.fmsimulator.model.competition.league.LeagueOf10;
import com.ogifmsim.fmsimulator.model.competition.league.LeagueOf12;
import com.ogifmsim.fmsimulator.model.competition.league.LeagueOf14;
import com.ogifmsim.fmsimulator.model.competition.league.LeagueOf8;

public class LeagueService {
    private final static String CSV_URL = "db_leagues.csv";
    
    private static LeagueService instance = null;
    private static List<League> leagues = null;

    private LeagueService() {
        leagues = loadAllLeagues(CSV_URL);
    }

    public static LeagueService getInstance() {
        if(instance == null) {
            instance = new LeagueService();
        }
        return instance;
    }

    public List<League> getAllLeagues() {
        if(leagues == null) {
            leagues = loadAllLeagues(CSV_URL);
        }
        return leagues;
    }

    public List<LeagueDTO> getAllLeaguesDTO() {
        List<LeagueDTO> leaguesDTO = new ArrayList<>();
        for(League league : getAllLeagues()) {
            leaguesDTO.add(new LeagueDTO(league));
        }
        return leaguesDTO;
    }

    private List<League> loadAllLeagues(String filename) {
       ArrayList<League> leagues = new ArrayList<>();
        
       try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                String loadedLeagueString = sc.nextLine();
                String[] leagueArray = loadedLeagueString.split(",");
                int leagueCapacity = Integer.parseInt(leagueArray[4]);

                League leagueNew = null;
                switch (leagueCapacity) {
                    case 8:
                        leagueNew = new LeagueOf8(leagueArray[3], leagueArray[1], 1, 1, leagueArray[6], Integer.parseInt(leagueArray[5]), null);
                        break;
                    case 10:
                        leagueNew = new LeagueOf10(leagueArray[3], leagueArray[1], 1, 1, leagueArray[6], Integer.parseInt(leagueArray[5]), null);
                        break;
                    case 12:
                        leagueNew = new LeagueOf12(leagueArray[3], leagueArray[1], 1, 1, leagueArray[6], Integer.parseInt(leagueArray[5]), null);
                        break;   
                    case 14:
                        leagueNew = new LeagueOf14(leagueArray[3], leagueArray[1], 1, 1, leagueArray[6], Integer.parseInt(leagueArray[5]), null);
                        break;
                    default:
                        System.out.println(" Was not able to create new League");
                        break;
                }
                
                leagues.add(leagueNew);
            }

            sc.close();
        } catch (FileNotFoundException | NumberFormatException e) {
            System.out.println(" Error: League Loader");
        }

        return leagues;
    }
}
