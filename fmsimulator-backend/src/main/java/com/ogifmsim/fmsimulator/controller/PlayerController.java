package com.ogifmsim.fmsimulator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogifmsim.fmsimulator.dto.PlayerDTO;
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

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Integer id) {
        PlayerDTO player = playerService.getPlayerByIdDTO(id);
        if(player == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(player);
    }
    
    @GetMapping("/all")
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayersDTO();
    }
    
    @GetMapping("/all/paginated")
    public ResponseEntity<Map<String, Object>> getAllPlayersByPage(
        @RequestParam int pageNumber, 
        @RequestParam int limit,
        @RequestParam(required = false) String sortingKey,
        @RequestParam(required = false, defaultValue = "asc") String sortingOrder,
        @RequestParam(required = false) String sortingOrientation) 
    {
        Map<String, Object> page = playerService.getAllPlayersByPageDTO(pageNumber, limit, sortingKey, sortingOrder, sortingOrientation);
        if(page == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(page);
    }
}
