package com.ogifmsim.fmsimulator.dto;

import com.ogifmsim.fmsimulator.model.player.Contract;

public class ContractDTO extends ArrangementDTO{
    private final double salary;
    private final int signDate, expireDate;
    
    public ContractDTO(Contract contract) {
        super(contract);
        this.salary = contract.getSalary();
        this.signDate = contract.getSignDate();
        this.expireDate = contract.getExpireDate();
    }

    public double getSalary() { return this.salary; }
    public int getSignDate() { return this.signDate; }
    public int getExpireDate() { return this.expireDate; }
}
