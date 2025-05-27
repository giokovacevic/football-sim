package com.ogifmsim.fmsimulator.dto;

import java.util.ArrayList;

import com.ogifmsim.fmsimulator.model.player.Player;
import com.ogifmsim.fmsimulator.model.team.Lineup;
import com.ogifmsim.fmsimulator.model.team.Roster;

public class RosterDTO {
    private final int capacity, currentSize;
    private final double currentSalaries;
    private final ArrayList<PlayerDTO> bench;
    private final LineupDTO lineup;

    public RosterDTO(Roster roster) {
        this.capacity = roster.getCapacity();
        this.currentSalaries = roster.getCurrentSalaries();
        this.bench = new ArrayList<>();
        for(Player player : roster.getBench()) {
            this.bench.add(new PlayerDTO(player));
        }
        this.lineup = (roster.getLineup() != null) ? new LineupDTO(roster.getLineup()) : null;
        this.currentSize = roster.getSizeCurrent();
    }

    public int getCapacity() { return this.capacity; }
    public int getCurrentSize() { return this.currentSize; }
    public double getCurrentSalaries() { return this.currentSalaries; }
    public ArrayList<PlayerDTO> getBench() { return this.bench; }
    public LineupDTO getLineup() { return this.lineup; }
}
