package com.ogifmsim.fmsimulator.model.enums;

public enum TacticalFormation {
    F_433("4-3-3", new Role[]{Role.GK, Role.LCB, Role.RCB, Role.LB, Role.RB, Role.DM, Role.LCM, Role.RCM, Role.LW, Role.RW, Role.CF}, 0, 0),
    F_442("4-4-2", new Role[]{Role.GK, Role.LCB, Role.RCB, Role.LB, Role.RB, Role.DM, Role.CM, Role.LM, Role.RM, Role.SS, Role.ST}, 0, 0),
    F_4231("4-2-3-1", new Role[]{Role.GK, Role.LCB, Role.RCB, Role.LB, Role.RB, Role.DM, Role.CM, Role.LAM, Role.RAM, Role.AM, Role.CF}, 0, 0),
    F_4411("4-4-1-1", new Role[]{Role.GK, Role.LCB, Role.RCB, Role.LB, Role.RB, Role.DM, Role.CM, Role.LM, Role.RM, Role.AM, Role.CF}, 1, (-1)),
    F_424("4-2-4", new Role[]{Role.GK, Role.LCB, Role.RCB, Role.LB, Role.RB, Role.DM, Role.CM, Role.LW, Role.RW, Role.SS, Role.ST}, (-3), 3),
    F_4213("4-2-1-3", new Role[]{Role.GK, Role.LCB, Role.RCB, Role.LB, Role.RB, Role.DM, Role.CM, Role.AM, Role.LW, Role.RW, Role.CF}, (-1), 1),
    F_352("3-5-2", new Role[]{Role.GK, Role.LCB, Role.RCB, Role.CB, Role.DM, Role.LCM, Role.RCM, Role.LM, Role.RM, Role.SS, Role.ST}, 0, 0),
    F_343("3-4-3", new Role[]{Role.GK, Role.LCB, Role.RCB, Role.CB, Role.DM, Role.CM, Role.LM, Role.RM, Role.LW, Role.RW, Role.CF}, (-1), 1),
    F_3412("3-4-1-2", new Role[]{Role.GK, Role.LCB, Role.RCB, Role.CB, Role.DM, Role.CM, Role.LM, Role.RM, Role.AM, Role.SS, Role.ST}, 0, 0),
    F_541("5-4-1", new Role[]{Role.GK, Role.LCB, Role.RCB, Role.CB, Role.LB, Role.RB, Role.DM, Role.CM, Role.LM, Role.RM, Role.CF}, 3, (-3)),
    F_532("5-3-2", new Role[]{Role.GK, Role.LCB, Role.RCB, Role.CB, Role.LB, Role.RB, Role.DM, Role.LCM, Role.RCM, Role.SS, Role.ST}, 1, (-1)),
    F_UD("undefined", new Role[]{Role.UD}, 0, 0);

    private final String id;
    private final Role[] requiredRoles;
    private final int  defensiveBonus, offensiveBonus;

    private TacticalFormation(String id, Role[] requiredRoles, int defensiveBonus, int offensiveBonus) {
        this.id = id;
        this.requiredRoles = requiredRoles;
        this.defensiveBonus = defensiveBonus;
        this.offensiveBonus = offensiveBonus;
    }

    public static TacticalFormation generateFormation(String formationId) {
        return switch (formationId) {
            case "4-3-3" ->
                TacticalFormation.F_433;
            case "4-4-2" ->
                TacticalFormation.F_442;
            case "4-2-3-1" ->
                TacticalFormation.F_4231;
            case "4-4-1-1" ->
                TacticalFormation.F_4411;
            case "4-2-4" ->
                TacticalFormation.F_424;
            case "4-2-1-3" ->
                TacticalFormation.F_4213;
            case "3-5-2" ->
                TacticalFormation.F_352;
            case "3-4-3" ->
                TacticalFormation.F_343;
            case "3-4-1-2" ->
                TacticalFormation.F_3412;
            case "5-4-1" ->
                TacticalFormation.F_541;
            case "5-3-2" ->
                TacticalFormation.F_532;
            default ->
                TacticalFormation.F_UD;
        };
    }

    public static String getRatingColor(int rating) { //new #b0fc0a  #14f200  //old #3cff26 #00bd52
        if(rating >= 95) {
            return "#00e0f5";
        }else if(rating >= 90) {
            return "#05ffe6";
        }else if(rating >= 85) {
            return "#14f200";
        }else if(rating >= 80) {
            return "#9df700";
        }else if(rating >= 75) {
            return "#ffde3b";
        }else if(rating >= 70) {
            return "#d9c17c";
        }else if(rating >= 65) {
            return "#ffa10a";
        }else if(rating >= 60) {
            return "#d17428";
        }else{
            return "#ff0000";
        }  
    }

    public void print() {
        System.out.printf("\n %s: ", this.id);
        for (Role role : this.requiredRoles) {
            System.out.print(" " + role.getStringValue());
        }
    }

    @Override
    public String toString() {
        return this.id;
    }

    public String getId() {
        return this.id;
    }

    public Role[] getRequiredRoles() {
        return this.requiredRoles;
    }

    public int getDefensiveBonus() {
        return this.defensiveBonus;
    }
    public int getOffensiveBonus() {
        return this.offensiveBonus;
    }
}
