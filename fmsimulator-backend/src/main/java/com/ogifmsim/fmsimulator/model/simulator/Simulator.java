package com.ogifmsim.fmsimulator.model.simulator;

import java.util.ArrayList;

import com.ogifmsim.fmsimulator.dto.RoleDTO;
import com.ogifmsim.fmsimulator.dto.TacticalFormationDTO;
import com.ogifmsim.fmsimulator.model.country.Country;
import com.ogifmsim.fmsimulator.model.enums.Role;
import com.ogifmsim.fmsimulator.model.enums.TacticalFormation;
import com.ogifmsim.fmsimulator.model.simulator.game.Game;

public class Simulator {
    private ArrayList<Game> games;
    //temporary data swapper


    public Simulator() {
        this.games = new ArrayList<>();

        // test:
        createNewGame();
    }

    public void createNewGame() {
        games.add(new Game(1));
    }
    public void loadGame() {

    }

    public ArrayList<Game> getGames() {
        return this.games;
    }


    //OLD
    public ArrayList<TacticalFormationDTO> getAllTacticalFormationDTOs() {
        ArrayList<TacticalFormationDTO> formations = new ArrayList<>();
        for (TacticalFormation formation : TacticalFormation.values()) {
            if (formation != TacticalFormation.F_UD) {
                formations.add(new TacticalFormationDTO(formation));
            }
        }
        return formations;
    }

    public ArrayList<Country> getAllCountries() {
        ArrayList<Country> countries = new ArrayList<Country>();
        this.games.get(0).getWorld().getCountries().forEach((key, value) -> {
            countries.add(value);
        });
        return countries;
    }

    public void postChangeFormation(String formationId) {
        this.games.get(0).getMyClub().getRoster().changeLineupFormation(
                TacticalFormation.generateFormation(formationId));
    }

    public void postSubstitute(String substitution) {
        substitution = substitution.replace("}", "");
        substitution = substitution.replace("{", "");
        substitution = substitution.replace("\"", "");
        String[] unformatedData = substitution.split(",");
        String mode = unformatedData[0].split(":")[1];
        String in = unformatedData[1].split(":")[1];
        String out = unformatedData[2].split(":")[1];
        switch (mode) {
            case "kick":
                this.games.get(0).getMyClub().getRoster().subOut(Role.generateRole(out));
                break;
            case "sub":
                System.out.println(in + "  " + out);
                this.games.get(0).getMyClub().getRoster().substitute(Integer.parseInt(in),
                        Role.generateRole(out));
                break;
            case "swap":
                System.out.println(in + "  " + out);
                this.games.get(0).getMyClub().getRoster().swap(Role.generateRole(in),
                        Role.generateRole(out));
                break;
            default:
                System.out.println("Error, no valid Mode");
        }
        postSaveLineup();
    }

    public void postSaveLineup() {
        this.games.get(0).getMyClub().getRoster().getLineup().save();
    }

}