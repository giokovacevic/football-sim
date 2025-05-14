package com.ogifmsim.fmsimulator.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogifmsim.fmsimulator.dto.TacticalFormationDTO;
import com.ogifmsim.fmsimulator.model.competition.Competitor;
import com.ogifmsim.fmsimulator.model.country.Country;
import com.ogifmsim.fmsimulator.model.simulator.Simulator;
import com.ogifmsim.fmsimulator.model.team.Club;

@RestController
@RequestMapping("api/football")
public class ControllerTest {
    private Simulator simulator;

    public ControllerTest() {
        this.simulator = new Simulator();
    }

   /*  @PostMapping("/simulate-matchday")
    public ResponseEntity<Void> simulateMatchDay() {
        simulator.getSavegame().simulateTest();

        return ResponseEntity.ok().build();
    }*/

    @PostMapping("/formation-change")
    public ResponseEntity<Void> postChangeFormation(@RequestBody String formationId) {
        formationId = formationId.replace("\"", "");
        simulator.postChangeFormation(formationId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/substitute")
    public ResponseEntity<Void> substitute(@RequestBody String substitution) {
        simulator.postSubstitute(substitution);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save-lineup")
    public ResponseEntity<Void> saveLineup() {
        simulator.postSaveLineup();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/countries-all")
    public ArrayList<Country> role() {
        return simulator.getAllCountries();
    }

    @GetMapping("/formations-all")
    public ArrayList<TacticalFormationDTO> getAllTacticalFormations() {
        return simulator.getAllTacticalFormationDTOs();
    }

    @GetMapping("/myclub")
    public Club getMyClub() {
        return simulator.getGames().get(0).getMyClub();
    }
}
