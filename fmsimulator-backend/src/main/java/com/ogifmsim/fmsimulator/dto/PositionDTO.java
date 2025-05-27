package com.ogifmsim.fmsimulator.dto;

import java.util.HashMap;

import com.ogifmsim.fmsimulator.model.enums.Position;

public class PositionDTO {
    private final String stringValue, name, type;
    private final int peakAge, decliningAge;

    public PositionDTO(Position position) {
        this.stringValue = position.getStringValue();
        this.name = position.getName();
        this.type = position.getType();
        this.peakAge = position.getPeakAge();
        this.decliningAge = position.getDecliningAge();
    }

    public String getStringValue() { return this.stringValue; }
    public String getName() { return this.name; }
    public String getType() { return this.type; }
    public int getPeakAge() { return this.peakAge; }
    public int getDecliningAge() { return this.decliningAge; }
}