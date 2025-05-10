package com.ogifm.simulator.entities.team;

import com.ogifm.simulator.entities.country.Country;
import com.ogifm.simulator.entities.player.Player;
import com.ogifm.simulator.enums.TacticalFormation;

public final class Club extends Team {

    private int rating;
    private double budgetMoney;
    private double transferMoney;

    public Club(String id, String name, String fullname, Country country, String preferredJersey,
            TacticalFormation formation, int rating, double transferMoney) {
        super("Club", id, name, fullname, country, preferredJersey, formation);
        this.rating = rating;
        this.setBudgetMoney(rating);
        this.transferMoney = transferMoney;
    }

    @Override
    public void print() {
        System.out.println(
                "\n===================================================================================================================");
        System.out.printf("%20s %5s   *%d*   Salaries: [ %6.2f / %6.3f ] $  Transfers: %8.3f $    Squad: (%d / %d)",
                getName(), getCountry().getId(), getRating(), roster.getCurrentSalaries(), getBudgetMoney(),
                getTransferMoney(), roster.getSizeCurrent(), roster.getCapacity());
        System.out.print(
                "\n===================================================================================================================");
        this.roster.print();
    }

    @Override
    public boolean addPlayer(Player player) {
        if (player != null) {
            if (this.roster.getSizeCurrent() >= this.roster.getCapacity()) {
                System.out.println("Can't Sign Player: " + player.getLastname() + ", Roster is full (25)");
                return false;
            }

            double salary = player.getContract().getSalary() + this.roster.getCurrentSalaries();
            if (salary > this.budgetMoney) {
                System.out.println("Can't Sign Player: " + player.getLastname() + ", Player too Expensive ($)");
                return false;
            }

            if (player.getContract().isStarting()) {
                this.roster.getLineup().setStarter(player.getContract().getRole(), player);
            } else {
                this.roster.getBench().add(player);
            }
            this.roster.setCurrentSalaries(salary);
            return true;
        }
        return false;
    }

    // Set
    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setBudgetMoney(int rating) {
        this.budgetMoney = 1500;
    }

    public void setTransferMoney(double transferMoney) {
        this.transferMoney = transferMoney;
    }

    // Get
    public int getRating() {
        return this.rating;
    }

    public double getBudgetMoney() {
        return this.budgetMoney;
    }

    public double getTransferMoney() {
        return this.transferMoney;
    }
}
