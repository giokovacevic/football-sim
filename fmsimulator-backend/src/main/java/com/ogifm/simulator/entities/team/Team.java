package com.ogifm.simulator.entities.team;

import com.ogifm.simulator.entities.country.Country;
import com.ogifm.simulator.entities.player.Player;
import com.ogifm.simulator.enums.TacticalFormation;

public class Team {

    private final String type;
    private final String id;
    protected String name;
    protected String fullname;
    protected Country country;
    protected String preferredJersey;
    // private Manager manager;

    protected Roster roster;

    public Team(String type, String id, String name, String fullname, Country country, String preferredJersey,
            TacticalFormation formation) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.fullname = fullname;
        this.country = country;
        this.preferredJersey = preferredJersey;
        this.roster = new Roster(formation);
    }

    public void print() {
        System.out.println(
                "\n===================================================================================================================");
        System.out.printf("%20s %5s Squad: (%d / %d)", getName(), getCountry().getId(), roster.getSizeCurrent(),
                roster.getCapacity());
        System.out.print(
                "\n===================================================================================================================");
        this.roster.print();
    }

    public boolean addPlayer(Player player) {
        if (player != null) {
            if (this.roster.getSizeCurrent() >= this.roster.getCapacity()) {
                System.out.println("Can't Sign Player: " + player.getLastname() + ", Roster is full (25)");
                return false;
            }

            if (player.getNationalArrangement().isStarting()) {
                this.roster.getLineup().setStarter(player.getNationalArrangement().getRole(), player);
            } else {
                this.roster.getBench().add(player);
            }
            return true;
        }
        return false;
    }

    public void setPreferredJersey(String preferredJersey) {
        this.preferredJersey = preferredJersey;
    }

    public String getType() {
        return this.type;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getFullname() {
        return this.fullname;
    }

    public Country getCountry() {
        return this.country;
    }

    public String getPreferredJersey() {
        return this.preferredJersey;
    }

    public Roster getRoster() {
        return this.roster;
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
    }
}
