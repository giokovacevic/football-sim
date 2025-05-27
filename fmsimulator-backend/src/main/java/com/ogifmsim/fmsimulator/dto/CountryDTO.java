package com.ogifmsim.fmsimulator.dto;

import com.ogifmsim.fmsimulator.model.country.Country;

public class CountryDTO {
    private final String id, name;

    public CountryDTO(Country country) {
        this.id = country.getId();
        this.name = country.getName();
    }

    public String getId() { return this.id; }
    public String getName() { return this.name; }
}
