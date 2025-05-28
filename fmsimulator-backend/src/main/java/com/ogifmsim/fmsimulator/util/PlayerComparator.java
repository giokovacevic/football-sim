package com.ogifmsim.fmsimulator.util;

import com.ogifmsim.fmsimulator.model.player.Player;

public class PlayerComparator {
    public static int compare(Player p1, Player p2, String sortingKey, String sortingOrientation) {
        switch (sortingKey) {
            case "rating":
                return compareByRating(p1, p2);
            case "lastname":
                return compareByLastname(p1, p2);
            case "currentAge":
                return compareByAge(p1, p2);
            case "country":
                return compareByCountry(p1, p2);
            case "positions":
                return sortingOrientation != null ? compareByPosition(p1, p2, sortingOrientation) : 0;
            case "contract":
                return sortingOrientation != null ? compareByContract(p1, p2, sortingOrientation) : 0;
            case "stamina":
                return compareByStamina(p1, p2);
            default:
                return 0;
        }
    }

    public static int compareByRating(Player p1, Player p2) {
        return p1.getRating() - p2.getRating();
    }
    public static int compareByAge(Player p1, Player p2) {
        return p1.getCurrentAge() - p2.getCurrentAge();
    }
    public static int compareByStamina(Player p1, Player p2) {
        return p1.getStamina() - p2.getStamina();
    }
    public static int compareByLastname(Player p1, Player p2) {
        return p1.getLastname().compareTo(p2.getLastname());
    }
    public static int compareByCountry(Player p1, Player p2) {
        return p1.getCountry().getName().compareTo(p2.getCountry().getName());
    }
    public static int compareByPosition(Player p1, Player p2, String sortingOrientation) { // TODO: fix
        boolean match1 = false;
        boolean match2 = false;
        switch (sortingOrientation) {
            case "mf":
                match1 = p1.getPreferredPositions().isMidfielder();
                match2 = p2.getPreferredPositions().isMidfielder();
                break;
            case "df":
                match1 = p1.getPreferredPositions().isDefender();
                match2 = p2.getPreferredPositions().isDefender();
                break;
            case "fw":
                match1 = p1.getPreferredPositions().isForward();
                match2 = p2.getPreferredPositions().isForward();
                break;
            case "gk":
                match1 = p1.getPreferredPositions().isGoalkeeper();
                match2 = p2.getPreferredPositions().isGoalkeeper();
                break;
            default:
                break;
        }
        if(match1 == match2) return 0;
        return match1 ? -1 : 1;
    }
    public static int compareByContract(Player p1, Player p2, String sortingOrientation) {
        if(p1.getContract() == null && p2.getContract() == null) return 0;
        if(p1.getContract() != null && p2.getContract() == null) return 1;
        if(p1.getContract() == null && p2.getContract() != null) return -1;
        if(sortingOrientation.equals("salary")) {   
            return (int)(p1.getContract().getSalary() - p2.getContract().getSalary());
        }else if(sortingOrientation.equals("expireDate")) {
            return p1.getContract().getExpireDate() - p2.getContract().getExpireDate();
        }else if(sortingOrientation.equals("teamId")) {
            String teamName1 = p1.getContract().getTeam() != null ? p1.getContract().getTeam().getName() : "Freeagent";
            String teamName2 = p2.getContract().getTeam() != null ? p2.getContract().getTeam().getName() : "Freeagent";
            return teamName1.compareTo(teamName2);
        }else{
            return 0;
        }
    }
}
