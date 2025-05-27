package com.ogifmsim.fmsimulator.model.enums;

public enum Position {
    GK("GK", "Goalkeeper", "gk", 28, 38),
    CB("CB", "Centre Back","df",27, 37),
    LB("LB","Left Back","df",26, 36),
    RB("RB","Right Back","df", 26, 36),
    DM("DM","Defensive Midfield","mf", 27, 37),
    CM("CM","Central Midfield","mf", 26, 36),
    LM("LM","Left Midfield","mf", 26, 36),
    RM("RM","Right Midfield","mf", 26, 36),
    AM("AM","Attacking Midfield","mf", 26, 36),
    LW("LW","Left Wing","fw", 26, 36),
    RW("RW","Right Wing","fw", 26, 36),
    CF("CF","Centre Forward","fw", 27, 37),
    SS("SS","Second Striker","fw", 26, 36),
    ST("ST","Striker","fw", 26, 36),
    UD("undefined","undefined","ud", 0, 0);

    private final String stringValue, name, type;
    private final int peakAge, decliningAge;

    private Position(String stringValue,String name, String type, int peakAge, int decliningAge) {
        this.stringValue = stringValue;
        this.name = name;
        this.type = type;
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

    public boolean isGoalkeeper() {
        return (this.type.equals("gk"));
    }

    public boolean isDefender() {
        return (this.type.equals("df"));
    }

    public boolean isMidfielder() {
        return (this.type.equals("mf"));
    }

    public boolean isForward() {
        return (this.type.equals("fw"));
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

    public String getType() {
        return this.type;
    }

    public int getPeakAge() {
        return this.peakAge;
    }

    public int getDecliningAge() {
        return this.decliningAge;
    }
}

