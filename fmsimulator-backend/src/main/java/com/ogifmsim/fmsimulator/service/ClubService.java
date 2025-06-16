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
import com.ogifmsim.fmsimulator.repository.preview.ClubRepository;

public class ClubService {

    private static ClubService instance = null;

    private ClubRepository clubRepository = ClubRepository.getInstance();

    private ClubService() { }

    public static ClubService getInstance() {
        if(instance == null) {
            instance = new ClubService();
        }
        return instance;
    }

    public List<Club> getAllClubs() {
        return clubRepository.loadAll();
    }

    public List<ClubDTO> getAllClubsDTO() {
        List<ClubDTO> clubsDTO = new ArrayList<>();
        for(Club club : getAllClubs()) {
            clubsDTO.add(new ClubDTO(club));
        }
        return clubsDTO;
    }

    public Club getClubById(String id) {
        return clubRepository.loadById(id);
    }

    public ClubDTO getClubByIdDTO(String id) {
        Club club = getClubById(id);
        if(club != null) return new ClubDTO(club);
        return null;
    }
}
