package com.ogifmsim.fmsimulator.model.player;

import com.ogifmsim.fmsimulator.model.enums.Role;
import com.ogifmsim.fmsimulator.model.team.Team;

public final class Contract extends Arrangement {

    private double salary;
    private int signDate;
    private int expireDate;

    public Contract(Team team, double salary, int signDate, int expireDate, int jerseyNumber, Role role) {
        super(team, jerseyNumber, role);
        setSalary(salary);
        setSignDate(signDate);
        setExpireDate(expireDate);
    }

    public Contract(Contract copy) {
        super(copy.getTeam(), copy.getJerseyNumber(), copy.getRole());
        this.salary = copy.getSalary();
        this.signDate = copy.getSignDate();
        this.expireDate = copy.getExpireDate();
    }

    public boolean hasExpired(int year) {
        return (this.expireDate == year);
    }

    public boolean isExpiring(int year) {
        return ((this.expireDate - 1) == year);
    }

    // Set
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setSignDate(int signDate) {
        this.signDate = signDate;
    }

    public void setExpireDate(int expireDate) {
        this.expireDate = expireDate;
    }

    // Get
    public double getSalary() {
        return this.salary;
    }

    public int getSignDate() {
        return this.signDate;
    }

    public int getExpireDate() {
        return this.expireDate;
    }

}
