package com.ogifmsim.fmsimulator.model.simulator.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.ogifmsim.fmsimulator.model.competition.Competition;
import com.ogifmsim.fmsimulator.model.country.Country;
import com.ogifmsim.fmsimulator.model.enums.Role;
import com.ogifmsim.fmsimulator.model.enums.TacticalFormation;
import com.ogifmsim.fmsimulator.model.player.Player;
import com.ogifmsim.fmsimulator.model.simulator.DataSwapper;
import com.ogifmsim.fmsimulator.model.team.Club;

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
