package com.ogifmsim.fmsimulator.model.simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.ogifmsim.fmsimulator.config.DatabaseConnection;
import com.ogifmsim.fmsimulator.model.competition.Competition;
import com.ogifmsim.fmsimulator.model.competition.Competitor;
import com.ogifmsim.fmsimulator.model.competition.league.*;
import com.ogifmsim.fmsimulator.model.country.Country;
import com.ogifmsim.fmsimulator.model.enums.Role;
import com.ogifmsim.fmsimulator.model.enums.TacticalFormation;
import com.ogifmsim.fmsimulator.model.player.Player;
import com.ogifmsim.fmsimulator.model.simulator.game.World;
import com.ogifmsim.fmsimulator.model.team.Club;

public class DataSwapper {
    private World world;
    private Club myClub;
    private ArrayList<Club> clubs;

    public DataSwapper() {
        swap();
    }

    public void swap() {
        this.world = new World(2024, null, 1);
        
        this.world.setCountries(loadAllCountries("db_countries.csv"));
        //insertCountries();
        
        //this.world.setLeagues(loadAllLeagues("db_leagues.csv"));
        //insertLeagues NOT DONE

       // this.world.setFreeAgents(loadAllPlayers("db_players.csv"));
        //insertPlayers();
        
        //clubs = loadAllClubs("db_clubs.csv");
        //insertClubs NOT DONE

        /*this.world.getLeagues().forEach(league -> {
            league.print();
        });*/
        
        //this.myClub = clubs.get(50); // ligue1 50 4248
    }

    public HashMap<String, Country> loadAllCountries(String filename) {
        HashMap<String, Country> mapOfCountries = new HashMap<>();
        String lastCountryLoadedId = "";
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                String loadedCountryString = sc.nextLine();
                String[] countryArray = loadedCountryString.split(",");
                Country country = new Country(countryArray[1], countryArray[2]);
                mapOfCountries.put(country.getId(), country);
                lastCountryLoadedId = country.getId();
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(" Error: Country Loader. Last Country loaded ID:  " + lastCountryLoadedId);
        }

        return mapOfCountries;
    }

    public ArrayList<League> loadAllLeagues(String filename) {
        ArrayList<League> leagues = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                String loadedLeagueString = sc.nextLine();
                String[] leagueArray = loadedLeagueString.split(",");
                int leagueCapacity = Integer.parseInt(leagueArray[4]);

                League leagueNew = null;
                switch (leagueCapacity) {
                    case 8:
                        leagueNew = new LeagueOf8(leagueArray[3], leagueArray[1], 1, 1, leagueArray[6], Integer.parseInt(leagueArray[5]), null);
                        break;
                    case 10:
                        leagueNew = new LeagueOf10(leagueArray[3], leagueArray[1], 1, 1, leagueArray[6], Integer.parseInt(leagueArray[5]), null);
                        break;
                    case 12:
                        leagueNew = new LeagueOf12(leagueArray[3], leagueArray[1], 1, 1, leagueArray[6], Integer.parseInt(leagueArray[5]), null);
                        break;   
                    case 14:
                        leagueNew = new LeagueOf14(leagueArray[3], leagueArray[1], 1, 1, leagueArray[6], Integer.parseInt(leagueArray[5]), null);
                        break;
                    default:
                        System.out.println(" Was not able to create new League");
                        break;
                }
                
                leagues.add(leagueNew);
            }

            sc.close();
        } catch (FileNotFoundException | NumberFormatException e) {
            System.out.println(" Error: League Loader");
        }

        return leagues;
    }

    /*public ArrayList<Player> loadAllPlayers(String filename) {
        ArrayList<Player> playerList = new ArrayList<>();
        // index 4 is a face;
        // change every index from 4 to +1 4 -> 5, 5 -> 6 ...
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                String loadedPlayerString = sc.nextLine();
                String[] playerArray = loadedPlayerString.split(",");
                Player playerNew = new Player(Integer.parseInt(playerArray[0]), playerArray[6], playerArray[5],
                        Integer.parseInt(playerArray[8]),
                        this.world.getCountries().get(playerArray[3]), playerArray[7],
                        (World.YEAR - Integer.parseInt(playerArray[9])), Integer.parseInt(playerArray[10]),
                        Integer.parseInt(playerArray[11]),
                        playerArray[13], Double.parseDouble(playerArray[14]), World.YEAR,
                        World.YEAR + Integer.parseInt(playerArray[15]),
                        Role.generateRole(playerArray[16]), Integer.parseInt(playerArray[4]),
                        Role.generateRole(playerArray[1]), 0, 100);
                playerList.add(playerNew);
                // System.out.println(playerNew.getId());
            }

            sc.close();
        } catch (FileNotFoundException | NumberFormatException e) {
            System.out.println(" Error: Player Loader");
        }

        return playerList;
    }*/

    /*public ArrayList<Club> loadAllClubs(String filename) {
        ArrayList<Club> clubList = new ArrayList<>();
        String lastIdLoaded = "";
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                String loadedClubString = sc.nextLine();
                String[] clubArray = loadedClubString.split(",");

                if (!(this.world.getCountries().containsKey(clubArray[7]))) {
                    clubArray[7] = "ALL";
                }
                Club clubNew = new Club(clubArray[9], clubArray[2], clubArray[3],
                        this.world.getCountries().get(clubArray[7]), clubArray[13],
                        TacticalFormation.generateFormation(clubArray[5]), Integer.parseInt(clubArray[4]), 100.0);

                String leagueId = clubArray[8];

                this.world.getFreeAgents().forEach((p) -> {
                    if (clubNew.getId().equals(p.getContract().getTeamId())) {
                        clubNew.addPlayer(p);
                    }
                });
                clubNew.getRoster().getLineup().save();
                lastIdLoaded = clubNew.getId();

                this.world.getLeagues().forEach(league -> {
                    if(league.getType().equals("League")) {
                        //System.out.println(leagueId);
                        if(league.getId().equals(leagueId)) {
                            league.getCompetitors().add(new Competitor(clubNew, 0, 0, 0, 0, 0));
                        }
                    }
                });

                clubList.add(clubNew);
            }

            sc.close();
        } catch (FileNotFoundException | NumberFormatException e) {

            System.out.println(" Error: Club Loader   (Last id loaded:   " + lastIdLoaded + ")");
        }

        this.world.getFreeAgents().removeIf(p -> !(p.getContract().getTeamId().equals("FA")));

        return clubList;
    }*/

    public World getWorld() {
        return this.world;
    }

    public Club getMyClub() {
        return this.myClub;
    }

    // SQL INSERTIONS:

    public void insertCountries() {
        String query = "INSERT INTO Country(`country_id`, `country_name`) VALUES (?, ?)";
        try(PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(query);) {
            this.world.getCountries().forEach((key, value) -> {
                try{
                statement.setString(1, value.getId());
                statement.setString(2, value.getName());
                if(statement.executeUpdate() < 1){
                    System.out.println(" Failed to insert: " + key + " in insertCountries()");
                }
                }catch(SQLException e) {
                    System.out.println(" Error: insertCountries(): " + key);
                }
                
            });

        } catch (SQLException e) {
            System.out.println(" Error: insertCountries()");
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    public void insertLeagues() {
        String query = "INSERT INTO League(`league_id`, `league_name`, `league_relegation`) VALUES (?, ?, ?)";
        try(PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(query)) {

        }catch(Exception e) {
            System.out.println(" Error in insertLeagues()");
        }finally{
            DatabaseConnection.closeConnection();
        }
    }

    public void insertClubs() {

    }

    public void insertPlayers() {
        String query = "INSERT INTO Player(`player_id`, `player_name`, `player_lastname`, `player_country_id`, `player_positions`, `player_rating`, `player_potential`, `player_birth_year`, `player_height`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; // , ?, ?, ?, ?, ?, ?
        try(PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(query)) { // , `player_contract_club_id`, `player_contract_salary`, `player_contract_sign_date`, `player_contract_expire_date`, `player_contract_role`, `player_contract_number`
                this.world.getFreeAgents().forEach(player -> {
                    try {
                        statement.setInt(1, player.getId());
                        statement.setString(2, player.getName());
                        statement.setString(3, player.getLastname());
                        statement.setString(4, player.getCountry().getId());
                        statement.setString(5, player.getPreferredPositions().toString());
                        statement.setInt(6, player.getRating());
                        statement.setInt(7, player.getPotential());
                        statement.setInt(8, player.getBirthYear());
                        statement.setInt(9, player.getHeight());
                        /*statement.setString(10, player.getContract().getTeamId());
                        statement.setDouble(11, player.getContract().getSalary());
                        statement.setInt(12, player.getContract().getSignDate());
                        statement.setInt(13, player.getContract().getExpireDate());
                        statement.setString(14, player.getContract().getRole().getStringValue());
                        statement.setInt(15, player.getContract().getJerseyNumber());*/

                        if(statement.executeUpdate() < 1) {
                            System.out.println(" Failed to insert: " + player.getLastname() + " in insertPlayers()");
                        }

                    } catch (Exception e) {
                        System.out.println(" Error: insertPlayers() : " + player.getLastname());
                    }
                });
        } catch (Exception e) {
            System.out.println(" Error in insertPlayers()");
        } finally{
            DatabaseConnection.closeConnection();
        }
    }
}
