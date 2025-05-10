package com.ogifm.simulator.entities.team;

import java.util.ArrayList;

import com.ogifm.simulator.entities.player.Player;
import com.ogifm.simulator.enums.Role;
import com.ogifm.simulator.enums.TacticalFormation;

public class Roster {

    private final int capacity;
    private double currentSalaries;

    private ArrayList<Player> bench;
    private Lineup lineup;

    // Roster methods
    public Roster(TacticalFormation formation) {
        this.capacity = 25;
        this.bench = new ArrayList<>();
        this.lineup = new Lineup(formation);
    }

    public void print() {
        for (Player player : this.bench) {
            System.out.println();
            player.print();
        }
        System.out.print(
                "\n-------------------------------------------------------------------------------------------------------------------");
        this.lineup.print();
        System.out.print(
                "\n===================================================================================================================\n");
    }

    public void changeLineupFormation(TacticalFormation formation) {
        this.lineup.getStarters().forEach((role, starter) -> {
            if (!starter.isEmpty()) {
                Player player = starter.getPlayer();
                this.lineup.setStarter(role, null);
                player.getContract().setRole(Role.BENCH);
                this.bench.add(player);
            }
        });
        this.lineup.setFormation(formation);
        this.lineup.save();
    }

    public void substitute(int playerId, Role roleOut) {
        subOut(roleOut);
        Player playerIn = getPlayer(playerId);
        this.lineup.setStarter(roleOut, playerIn);
        if (playerIn != null) {
            for (int i = 0; i < this.bench.size(); i++) {
                if (this.bench.get(i).getId() == playerIn.getId()) {
                    this.bench.remove(i);
                }
            }
        }
    }

    public void subOut(Role role) {
        Player player = this.lineup.getStarters().get(role).getPlayer();
        if (player != null) {
            player.getContract().setRole(Role.BENCH);
            this.bench.add(player);
        }
        this.lineup.setStarter(role, null);
    }

    public void swap(Role roleIn, Role roleOut) {
        Player playerOut = this.lineup.getStarters().get(roleIn).getPlayer();
        Player playerIn = this.lineup.getStarters().get(roleOut).getPlayer();

        this.lineup.setStarter(roleOut, playerOut);
        this.lineup.setStarter(roleIn, playerIn);
    }

    public Player getPlayer(int playerId) {
        for (Player player : this.bench) {
            if (player.getId() == playerId) {
                return player;
            }
        }
        for (Role key : this.lineup.getStarters().keySet()) {
            if (!(this.lineup.getStarters().get(key).isEmpty())) {
                Player temp = this.lineup.getStarters().get(key).getPlayer();
                if (temp.getId() == playerId) {
                    return temp;
                }
            }
        }
        return null;
    }

    // Set
    public void setCurrentSalaries(double currentSalaries) {
        this.currentSalaries = currentSalaries;
    }

    // Get
    public int getCapacity() {
        return this.capacity;
    }

    public int getSizeCurrent() {
        int inLineup = 0;
        for (Role key : this.lineup.getStarters().keySet()) {
            if (!(this.lineup.getStarters().get(key).isEmpty())) {
                inLineup++;
            }
        }
        return (inLineup + this.bench.size());
    }

    public double getCurrentSalaries() {
        return this.currentSalaries;
    }

    public ArrayList<Player> getBench() {
        return this.bench;
    }

    public Lineup getLineup() {
        return this.lineup;
    }
}