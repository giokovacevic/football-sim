package com.ogifm.simulator.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.ogifm.simulator.DataSwapper;
import com.ogifm.simulator.entities.competition.Competition;
import com.ogifm.simulator.entities.country.Country;
import com.ogifm.simulator.entities.player.Player;
import com.ogifm.simulator.entities.team.Club;
import com.ogifm.simulator.enums.Role;
import com.ogifm.simulator.enums.TacticalFormation;

public class Game {
    private int id;
    private World world;
    private Club myClub;
    private ArrayList<Competition> myCompetitions;

    public Game(int id) {
        this.id = id;
        DataSwapper swapper = new DataSwapper();
        this.world = swapper.getWorld();
        this.myClub = swapper.getMyClub();
    }

    // Setters
    public void setWorld(World world) {
        this.world = world;
    }

    // Getters
    public int getId() {
        return this.id;
    }

    public World getWorld() {
        return this.world;
    }

    public Club getMyClub() {
        return this.myClub;
    }

    public ArrayList<Competition> getMyCompetitions() {
        return this.myCompetitions;
    }

}
