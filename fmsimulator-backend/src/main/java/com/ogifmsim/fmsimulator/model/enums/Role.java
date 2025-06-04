package com.ogifmsim.fmsimulator.model.enums;

public enum Role {
    GK("GK", 325, 560, Position.GK),

    CB("CB", 325, 470, Position.CB),
    LCB("LCB", 170, 470, Position.CB),
    RCB("RCB", 480, 470, Position.CB),
    LB("LB", 10, 440, Position.LB),
    RB("RB", 640, 440, Position.RB),

    DM("DM", 325, 370, Position.DM),
    CM("CM", 325, 260, Position.CM),
    LCM("LCM", 170, 260, Position.CM),
    RCM("RCM", 480, 260, Position.CM),
    LM("LM", 10, 260, Position.LM),
    RM("RM", 640, 260, Position.RM),

    AM("AM", 325, 150, Position.AM),
    LAM("LAM", 170, 150, Position.AM),
    RAM("RAM", 480, 150, Position.AM),

    LW("LW", 10, 80, Position.LW),
    RW("RW", 640, 80, Position.RW),

    ST("ST", 170, 20, Position.ST),
    SS("SS", 480, 20, Position.SS),
    CF("CF", 325, 20, Position.CF),

    BENCH("sub", 0, 0, Position.UD),
    UD("undefined", 0, 0, Position.UD);

    private final String stringValue;
    private final int x, y;
    private final Position positionValue;

    private Role(String stringValue, int x, int y, Position positionValue) {
        this.stringValue = stringValue;
        this.x = x;
        this.y = y;
        this.positionValue = positionValue;
    }

    public static Role generateRole(String role) {
        return switch (role) {
            case "GK" ->
                Role.GK;
            case "CB" ->
                Role.CB;
            case "LCB" ->
                Role.LCB;
            case "RCB" ->
                Role.RCB;
            case "LB" ->
                Role.LB;
            case "RB" ->
                Role.RB;
            case "DM" ->
                Role.DM;
            case "CM" ->
                Role.CM;
            case "LCM" ->
                Role.LCM;
            case "RCM" ->
                Role.RCM;
            case "LM" ->
                Role.LM;
            case "RM" ->
                Role.RM;
            case "AM" ->
                Role.AM;
            case "LAM" ->
                Role.LAM;
            case "RAM" ->
                Role.RAM;
            case "LW" ->
                Role.LW;
            case "RW" ->
                Role.RW;
            case "SS" ->
                Role.SS;
            case "ST" ->
                Role.ST;
            case "CF" ->
                Role.CF;
            case "sub" ->
                Role.BENCH;
            default ->
                Role.UD;
        };
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Position getPositionValue() {
        return this.positionValue;
    }
}
