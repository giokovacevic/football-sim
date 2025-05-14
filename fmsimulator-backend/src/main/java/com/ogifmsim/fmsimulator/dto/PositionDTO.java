package com.ogifmsim.fmsimulator.dto;

import java.util.HashMap;

import com.ogifmsim.fmsimulator.model.enums.Position;

public class PositionDTO {
    private final String stringValue, color, name;
    private final int peakAge, decliningAge;

    public PositionDTO(Position position) {
        this.stringValue = position.getStringValue();
        this.name = position.getName();
        this.peakAge = position.getPeakAge();
        this.decliningAge = position.getDecliningAge();
        this.color = position.getColor();
    }

    public String getColor() {
        return this.color;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public String getName() {
        return this.name;
    }

    public int getPeakAge() {
        return this.peakAge;
    }

    public int getDecliningAge() {
        return this.decliningAge;
    }
}
