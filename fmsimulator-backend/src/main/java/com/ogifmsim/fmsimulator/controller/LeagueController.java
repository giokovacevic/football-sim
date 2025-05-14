package com.ogifmsim.fmsimulator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogifmsim.fmsimulator.model.competition.league.League;
import com.ogifmsim.fmsimulator.service.LeagueService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/football/leagues")
public class LeagueController {
    private LeagueService leagueService = LeagueService.getInstance();
    
    @GetMapping("/all")
    public List<League> getAllLeagues() {
        return leagueService.getAllLeagues();
    }
    
}
