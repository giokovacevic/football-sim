package com.ogifmsim.fmsimulator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogifmsim.fmsimulator.dto.PlayerDTO;
import com.ogifmsim.fmsimulator.model.player.Player;
import com.ogifmsim.fmsimulator.service.PlayerService;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/football/players")
public class PlayerController {
    PlayerService playerService = PlayerService.getInstance();
    
    @GetMapping("/all")
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayersDTO();
    }
    
    @GetMapping("/all/paginated")
    public ResponseEntity<Map<String, Object>> getAllPlayersByPage(@RequestParam int pageNumber, @RequestParam int limit) {
        Map<String, Object> page = playerService.getAllPlayersByPageDTO(pageNumber, limit);
        if(page == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(page);
    }
}
