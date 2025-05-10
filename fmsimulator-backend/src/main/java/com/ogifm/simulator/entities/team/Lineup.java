package com.ogifm.simulator.entities.team;

import java.util.HashMap;

import com.ogifm.simulator.dto.PositionDTO;
import com.ogifm.simulator.dto.RoleDTO;
import com.ogifm.simulator.dto.TacticalFormationDTO;
import com.ogifm.simulator.entities.player.Player;
import com.ogifm.simulator.enums.Position;
import com.ogifm.simulator.enums.Role;
import com.ogifm.simulator.enums.TacticalFormation;

public final class Lineup {

    private TacticalFormation formation;
    private boolean viable;
    private int offense, defense;
    private HashMap<Role, Starter> starters;

    public Lineup(TacticalFormation formation) {
        this.starters = new HashMap<>();
        this.setFormation(formation);
        this.offense = 0;
        this.defense = 0;
        this.viable = false;
    }

    public void print() {
        System.out.printf("\n  Formation:  %10s   |   Defense:  %2d   |  Offense:  %2d  |", formation.getId(),
                this.defense, this.offense);
        System.out.print(
                "\n-------------------------------------------------------------------------------------------------------------------");
        this.starters.forEach((key, lm) -> {
            System.out.printf("\n  %4s: ", key);
            if (lm.isEmpty()) {
                System.out.printf("%18s", "...............");
            } else {
                lm.getPlayer().print();
            }
        });
    }

    public void save() {
        int defenseCount = 0;
        int offenseCount = 0;
        double defenseTotal = 0;
        double offenseTotal = 0;
        this.viable = true;

        for (Role role : this.starters.keySet()) {
            if (this.starters.get(role).isEmpty()) {
                this.viable = false;
                break;
            } else {
                Position pos = role.getPositionValue();
                if (pos.isGoalkeeper() || pos.isDefender()) {
                    defenseCount++;
                    defenseTotal = defenseTotal + this.starters.get(role).getCurrentRating();
                } else {
                    offenseCount++;
                    offenseTotal = offenseTotal + this.starters.get(role).getCurrentRating();
                }
            }
        }

        if (viable == true) {
            defense = (int) Math.round(defenseTotal / defenseCount);
            defense = defense + this.formation.getDefensiveBonus();
            offense = (int) Math.round(offenseTotal / offenseCount);
            offense = offense + this.formation.getOffensiveBonus();
        } else {
            defense = 0;
            offense = 0;
        }
        // System.out.printf("\n Lineup saved with DEF: %3d ATK: %3d", defense,
        // offense);
    }

    // Set
    public void setFormation(TacticalFormation formation) {
        this.formation = null;
        if ((formation != null)) {
            this.formation = formation;
            this.starters = new HashMap<>();
            for (Role requiredRole : formation.getRequiredRoles()) {
                this.starters.put(requiredRole, new Starter());
            }
        } else {
            // make an error or soemthing
            System.out.println("Can't Set a Formation when Formation is already Set.");
        }
    }

    public void setStarter(Role requiredRole, Player player) {
        if (player != null) {
            boolean outOfPosition = true;
            Position requiredPosition = requiredRole.getPositionValue();
            for (Position positionTemp : player.getPreferredPositions().getPositions()) {
                if (positionTemp == requiredPosition) {
                    outOfPosition = false;
                    break;
                }
            }
            player.getContract().setRole(requiredRole);
            this.starters.get(requiredRole).setOutOfPosition(outOfPosition);
        }
        this.starters.get(requiredRole).setPlayer(player);
    }

    // Get
    public TacticalFormation getFormation() {
        return this.formation;
    }

    public TacticalFormationDTO getFormationDTO() {
        return new TacticalFormationDTO(this.formation);
    }

    public boolean isViable() {
        return this.viable;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getOffense() {
        return this.offense;
    }

    public HashMap<Role, Starter> getStarters() {
        return this.starters;
    }

    public String getDefenseRatingColor() {
        return TacticalFormation.getRatingColor(defense);
    }

    public String getOffenseRatingColor() {
        return TacticalFormation.getRatingColor(offense);
    }
}
