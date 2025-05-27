package com.ogifmsim.fmsimulator.dto;

import com.ogifmsim.fmsimulator.model.team.Club;

public class ClubDTO extends TeamDTO{
    private final int rating;
    private final double budgetMoney, transferMoney;

    public ClubDTO(Club club) {
        super(club);
        this.rating = club.getRating();
        this.budgetMoney = club.getBudgetMoney();
        this.transferMoney = club.getTransferMoney();
    }

    public int getRating() { return this.rating; }
    public double getBudgetMoney() { return this.budgetMoney; }
    public double getTransferMoney() { return this.transferMoney; }
}
