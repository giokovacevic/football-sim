package com.ogifmsim.fmsimulator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogifmsim.fmsimulator.dto.LeagueDTO;
import com.ogifmsim.fmsimulator.service.LeagueService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/football/leagues")
public class LeagueController {
    private LeagueService leagueService = LeagueService.getInstance();
    
    @GetMapping("/all")
    public List<LeagueDTO> getAllLeagues() {
        return leagueService.getAllLeaguesDTO();
    }
    
}
