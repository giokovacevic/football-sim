package com.ogifm.simulator.entities.player;

import com.ogifm.simulator.entities.country.Country;
import com.ogifm.simulator.enums.Role;
import com.ogifm.simulator.enums.TacticalFormation;
import com.ogifm.simulator.game.World;

public final class Player {

    private int id;
    private String name;
    private String lastname;
    private int rating;
    private int potential;
    private Country country;
    private PreferredPositions preferredPositions;
    private int birthYear;
    private int height;
    // private Status status;
    private int statusDuration;
    private Contract contract; // re do?
    private Arrangement nationalArrangement;
    private int stamina;

    public Player(int id, String name, String lastname, int rating, Country country, String positions, int birthYear,
            int height, int potential, String clubId, double salary, int signDate, int expireDate, Role role,
            int clubJerseyNumber, Role nationalTeamRole, int nationalTeamJerseyNumber, int stamina) {
        setId(id);
        setName(name);
        setLastname(lastname);
        setRating(rating);
        setCountry(country);
        setBirthYear(birthYear);
        setHeight(height);
        setPotential(potential);
        this.preferredPositions = new PreferredPositions(positions);
        setStatusDuration(0); // Status and status duration are not done ...
        setContract(clubId, salary, signDate, expireDate, clubJerseyNumber, role);
        setNationalArrangement(country.getId().toLowerCase(), nationalTeamJerseyNumber, role);
        setStamina(stamina);
    }

    public Player(Player copy) {
        this.id = copy.getId();
        this.name = copy.getName();
        this.lastname = copy.getLastname();
        this.rating = copy.getRating();
        this.potential = copy.getPotential();
        this.preferredPositions = new PreferredPositions(copy.getPreferredPositions());
        this.country = copy.getCountry();
        this.birthYear = copy.getBirthYear();
        this.statusDuration = copy.getStatusDuration();
        this.contract = new Contract(copy.getContract());
        this.nationalArrangement = new Arrangement(copy.getNationalArrangement());
        this.stamina = copy.getStamina();
    }

    public void print() {
        System.out.printf("%18s %6s %6d %9d %9.2f $  [%d]      %s", this.lastname, this.country.getId(),
                this.rating, this.birthYear, getContract().getSalary(), getContract().getExpireDate(),
                this.preferredPositions.toString());
    }

    public String getRatingColor() {
        return TacticalFormation.getRatingColor(rating);
    }

    // Set
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setPotential(int potential) {
        this.potential = potential;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setPreferredPositions(String positions) {
        this.preferredPositions = new PreferredPositions(positions);
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setStatusDuration(int statusDuration) {
        this.statusDuration = statusDuration;
    }

    public void setContract(String teamId, double salary, int signDate, int expireDate, int jerseyNumber, Role role) {
        this.contract = new Contract(teamId, salary, signDate, expireDate, jerseyNumber, role);
    }

    public void setNationalArrangement(String teamId, int jerseyNumber, Role role) {
        this.nationalArrangement = new Arrangement(teamId, jerseyNumber, role);
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    // Get
    public int getProjectedPotential() {
        if (this.potential >= 85) {
            return 5;
        } else if (this.potential >= 77) {
            return 4;
        } else if (this.potential >= 70) {
            return 3;
        } else if (this.potential >= 61) {
            return 2;
        } else {
            return 1;
        }
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLastname() {
        return this.lastname;
    }

    public int getRating() {
        return this.rating;
    }

    public int getPotential() {
        return this.potential;
    }

    public Country getCountry() {
        return this.country;
    }

    public PreferredPositions getPreferredPositions() {
        return this.preferredPositions;
    }

    public int getBirthYear() {
        return this.birthYear;
    }

    public int getCurrentAge() {
        return World.YEAR - this.birthYear;
    }

    public int getHeight() {
        return this.height;
    }

    public String getHeightStringInMeters() {
        return String.valueOf((((double) this.height) / 100));
    }

    public String getHeightStringInFeet() {
        double inTotal = this.height / 2.54;
        double ftDouble = inTotal / 12;
        int ft = (((ftDouble % 1) > 0.98) ? (int) Math.round(ftDouble) : (int) Math.floor(ftDouble));
        int in = (inTotal - (ft * 12) > 10.99) ? (int) Math.floor(inTotal - (ft * 12))
                : (int) Math.round(inTotal - (ft * 12));
        return String.valueOf(ft) + "." + String.valueOf(in) + "'";
    }

    public int getStatusDuration() {
        return this.statusDuration;
    }

    public Contract getContract() {
        return this.contract;
    }

    public int getStamina() {
        return this.stamina;
    }

    public Arrangement getNationalArrangement() {
        return this.nationalArrangement;
    }
}
