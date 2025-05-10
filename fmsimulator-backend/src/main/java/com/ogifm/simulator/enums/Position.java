package com.ogifm.simulator.enums;

public enum Position {
    GK("GK", "Goalkeeper", 28, 38),
    CB("CB", "Centre Back",27, 37),
    LB("LB","Left Back",26, 36),
    RB("RB","Right Back", 26, 36),
    DM("DM","Defensive Midfield", 27, 37),
    CM("CM","Central Midfield", 26, 36),
    LM("LM","Left Midfield", 26, 36),
    RM("RM","Right Midfield", 26, 36),
    AM("AM","Attacking Midfield", 26, 36),
    LW("LW","Left Wing", 26, 36),
    RW("RW","Right Wing", 26, 36),
    CF("CF","Centre Forward", 27, 37),
    SS("SS","Second Striker", 26, 36),
    ST("ST","Striker", 26, 36),
    UD("undefined","undefined", 0, 0);

    private final String stringValue, name;
    private final int peakAge, decliningAge;

    private Position(String stringValue,String name, int peakAge, int decliningAge) {
        this.stringValue = stringValue;
        this.name = name;
        this.peakAge = peakAge;
        this.decliningAge = decliningAge;
    }

    public static Position generatePosition(String position) {
        return switch (position) {
            case "GK" ->
                Position.GK;
            case "CB" ->
                Position.CB;
            case "LB" ->
                Position.LB;
            case "RB" ->
                Position.RB;
            case "DM" ->
                Position.DM;
            case "CM" ->
                Position.CM;
            case "LM" ->
                Position.LM;
            case "RM" ->
                Position.RM;
            case "AM" ->
                Position.AM;
            case "LW" ->
                Position.LW;
            case "RW" ->
                Position.RW;
            case "CF" ->
                Position.CF;
            case "SS" ->
                Position.SS;
            case "ST" ->
                Position.ST;
            default ->
                Position.UD;
        };
    }

    public String getColor() {
        if (this.isMidfielder()) {
            return "#fa332f";
        } else if (this.isDefender()) {
            return "#02d65a";
        } else if (this.isForward()) {
            return "#409cff";
        } else if (this.isGoalkeeper()) {
            return "#ffbe19";
        } else {
            return "#4f4f4f";
        }
    }

    public boolean isGoalkeeper() {
        return (this == Position.GK);
    }

    public boolean isDefender() {
        return ((this == Position.CB) || (this == Position.LB) || (this == Position.RB));
    }

    public boolean isMidfielder() {
        return ((this == Position.DM) || (this == Position.CM) || (this == Position.AM) || (this == Position.LM) || (this == Position.RM));
    }

    public boolean isForward() {
        return ((this == Position.CF) || (this == Position.LW) || (this == Position.RW) || (this == Position.SS) || (this == Position.ST));
    }

    @Override
    public String toString() {
        return this.stringValue;
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

