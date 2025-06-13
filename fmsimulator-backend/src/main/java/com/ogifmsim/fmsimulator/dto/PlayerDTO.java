package com.ogifmsim.fmsimulator.dto;

import java.util.ArrayList;

import com.ogifmsim.fmsimulator.model.enums.Position;
import com.ogifmsim.fmsimulator.model.player.Contract;
import com.ogifmsim.fmsimulator.model.player.Player;

public class PlayerDTO{
    private final int id, rating, potential, birthYear, height, stamina, projectedPotential, currentAge;
    private final String name, lastname;
    private final CountryDTO country;
    private final ArrayList<PositionDTO> positions;
    private final ContractDTO contract;
    private final ArrangementDTO nationalArrangement;
    
    public PlayerDTO(Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.lastname = player.getLastname();
        this.rating = player.getRating();
        this.potential = player.getPotential();
        this.birthYear = player.getBirthYear();
        this.height = player.getHeight();
        this.stamina = player.getStamina();
        this.country = (player.getCountry() != null) ? new CountryDTO(player.getCountry()) : null;
        this.positions = new ArrayList<>();
        if(player.getPreferredPositions() != null) {
            for(Position position : player.getPreferredPositions().getPositions()) {
            this.positions.add(new PositionDTO(position));
        }
        }
        this.contract = (player.getContract() != null) ? new ContractDTO(player.getContract()) : new ContractDTO(new Contract(null, 0.0, 0, -1*Integer.MAX_VALUE, 0, null));
        this.nationalArrangement = (player.getNationalArrangement() != null) ? new ArrangementDTO(player.getNationalArrangement()) : null;
        this.projectedPotential = player.getProjectedPotential();
        this.currentAge = player.getCurrentAge();
    }

    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public String getLastname() { return this.lastname; }
    public int getRating() { return this.rating; }
    public CountryDTO getCountry() { return this.country; }
    public int getPotential() { return this.potential; }
    public int getBirthYear() { return this.birthYear; }
    public int getHeight() { return this.height; }
    public int getStamina() { return this.stamina; }
    public ArrayList<PositionDTO> getPositions() { return this.positions; }
    public ContractDTO getContract() { return this.contract; }
    public ArrangementDTO getNationalArrangement() { return this.nationalArrangement; }
    public int getProjectedPotential() { return this.projectedPotential;}
    public int getCurrentAge() { return this.currentAge; }
}