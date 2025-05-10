package com.ogifm.simulator.entities.country;

public final class Country {

    private String id;
    private String name;

    public Country(String id, String name) {
        setId(id);
        setName(name);
    }

    public Country(Country copy) {
        setId(copy.getId());
        setName(copy.getName());
    }

    public void print() {
        System.out.println("[" + getId() + "]: " + getName());
    }

    // Set
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Get 
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}

