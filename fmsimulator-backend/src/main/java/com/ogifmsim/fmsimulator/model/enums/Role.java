package com.ogifmsim.fmsimulator.model.enums;

public enum Role {
    GK("GK", 295, 460, Position.GK),
    CB("CB", 295, 370, Position.CB),
    LCB("LCB", 150, 370, Position.CB),
    RCB("RCB", 440, 370, Position.CB),
    LB("LB", 5, 330, Position.LB),
    RB("RB", 585, 330, Position.RB),
    DM("DM", 295, 280, Position.DM),
    CM("CM", 295, 190, Position.CM),
    LCM("LCM", 150, 190, Position.CM),
    RCM("RCM", 440, 190, Position.CM),
    LM("LM", 5, 190, Position.LM),
    RM("RM", 585, 190, Position.RM),
    AM("AM", 295, 100, Position.AM),
    LAM("LAM", 150, 100, Position.AM),
    RAM("RAM", 440, 100, Position.AM),
    LW("LW", 5, 45, Position.LW),
    RW("RW", 585, 45, Position.RW),
    SS("SS", 440, 5, Position.SS),
    ST("ST", 150, 5, Position.ST),
    CF("CF", 295, 5, Position.CF),
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
