package com.ogifmsim.fmsimulator.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogifmsim.fmsimulator.dto.ClubDTO;
import com.ogifmsim.fmsimulator.model.player.Player;
import com.ogifmsim.fmsimulator.model.team.Club;
import com.ogifmsim.fmsimulator.service.ClubService;

@RestController
@RequestMapping("api/football/clubs")
public class ClubController {
    private ClubService clubService = ClubService.getInstance();
    
    @GetMapping("/all")
    public List<ClubDTO> getAllClubs() {
        return clubService.getAllClubsDTO();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubDTO> getClubById(@PathVariable String id) {
        ClubDTO clubDTO = clubService.getClubByIdDTO(id);
        if(clubDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clubDTO);
    }
}
