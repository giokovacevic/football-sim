package com.ogifm.simulator.entities.player;

import java.util.ArrayList;

import com.ogifm.simulator.dto.PositionDTO;
import com.ogifm.simulator.enums.Position;

public class PreferredPositions {

    private ArrayList<Position> positions;

    public PreferredPositions() {
        this.positions = new ArrayList<>();
    }

    public PreferredPositions(String positionsString) {
        this.positions = new ArrayList<>();
        String[] positionsArray = positionsString.split(" ");
        for (String element : positionsArray) {
            positions.add(Position.generatePosition(element));
        }
    }

    public PreferredPositions(PreferredPositions copy) {
        this.positions = new ArrayList<>();
        for (int i = 0; i < copy.getPositions().size(); i++) {
            this.positions.add(copy.getPositions().get(i));
        }
    }

    @Override
    public String toString() {
        String s = "";
        s = s + this.positions.get(0).getStringValue();
        for (int i = 1; i < this.positions.size(); i++) {
            s = s + " " + this.positions.get(i).getStringValue();
        }
        return s;
    }

    // Set

    // Get
    public ArrayList<Position> getPositions() {
        return this.positions;
    }

    public Position getPrimaryPosition() {
        return positions.get(0);
    }

    public ArrayList<PositionDTO> getPositionDTOs() {
        ArrayList<PositionDTO> positionDTOs = new ArrayList<>();
        this.positions.forEach(position -> {
            positionDTOs.add(new PositionDTO(position));
        });
        return positionDTOs;
    }

}
