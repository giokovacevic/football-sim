package com.ogifmsim.fmsimulator.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ogifmsim.fmsimulator.dto.ClubDTO;
import com.ogifmsim.fmsimulator.model.competition.Competitor;
import com.ogifmsim.fmsimulator.model.competition.league.League;
import com.ogifmsim.fmsimulator.model.country.Country;
import com.ogifmsim.fmsimulator.model.enums.TacticalFormation;
import com.ogifmsim.fmsimulator.model.player.Player;
import com.ogifmsim.fmsimulator.model.team.Club;

public class ClubService {
    private final static String CSV_URL = "db_clubs.csv";

    private static ClubService instance = null;
    private static CountryService countryService = CountryService.getInstance();
    private static LeagueService leagueService = LeagueService.getInstance();
    private static List<Club> clubs = null;

    private ClubService() {
         if(clubs == null) clubs = loadAllClubs(CSV_URL);
    }

    public static ClubService getInstance() {
        if(instance == null) {
            instance = new ClubService();
        }
        return instance;
    }

    public List<Club> getAllClubs() {
        if(clubs == null) {
            clubs = loadAllClubs(CSV_URL);
        }
        return clubs;
    }

    public List<ClubDTO> getAllClubsDTO() {
        List<ClubDTO> clubsDTO = new ArrayList<>();
        for(Club club : getAllClubs()) {
            clubsDTO.add(new ClubDTO(club));
        }
        return clubsDTO;
    }

    public Club getClubById(String id) {
        for(Club club : getAllClubs()) {
            if(club.getId().equals(id)) return club;
        }
        return null;
    }

    public ClubDTO getClubByIdDTO(String id) {
        Club club = getClubById(id);
        if(club != null) return new ClubDTO(club);
        return null;
    }

    private List<Club> loadAllClubs(String filename) {
        ArrayList<Club> clubList = new ArrayList<>();
        String lastIdLoaded = "";
        
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                String loadedClubString = sc.nextLine();
                String[] clubArray = loadedClubString.split(",");

                Country country = countryService.getCountryById(clubArray[7]);
                if (country == null) {
                    country = countryService.getCountryById("ALL");
                }
                
                Club clubNew = new Club(clubArray[9], clubArray[2], clubArray[3],
                        country, clubArray[13],
                        TacticalFormation.generateFormation(clubArray[5]), Integer.parseInt(clubArray[4]), 100.0);

                String leagueId = clubArray[8];
                
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
