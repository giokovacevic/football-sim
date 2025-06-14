package com.ogifmsim.fmsimulator.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ogifmsim.fmsimulator.config.DatabaseConnection;
import com.ogifmsim.fmsimulator.model.country.Country;
import com.ogifmsim.fmsimulator.model.enums.Role;
import com.ogifmsim.fmsimulator.model.enums.TacticalFormation;
import com.ogifmsim.fmsimulator.model.player.Contract;
import com.ogifmsim.fmsimulator.model.player.Player;
import com.ogifmsim.fmsimulator.model.team.Club;
import com.ogifmsim.fmsimulator.model.team.Team;

public class PlayerRepository {
    private static PlayerRepository instance = null;

    private static CountryRepository countryRepository = CountryRepository.getInstance();

    private PlayerRepository() { }

    public static PlayerRepository getInstance() {
        if(instance == null) instance = new PlayerRepository();
        return instance;
    }
    
    public List<Player> loadAllPlayers() {
        List<Player> players = new ArrayList<>();
        Map<String, Team> teamsCache = new HashMap<>();

        String query = "SELECT * FROM player" +
        " INNER JOIN country on player_country_id = country_id" +
        " LEFT JOIN Contract on player_id = contract_player_id" + 
        " LEFT join club on contract_club_id = club_team_id" +
        " LEFT JOIN team on club_team_id = team_id" +
        " LEFT JOIN Country t on team_country_id = t.country_id;";

        try(PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery()) {

            while(rs.next()) {
                Country playerCountry = countryRepository.extractCountry(rs, "");

                Contract playerContract = null;
                Team playerClub = null;
                String teamId = rs.getString("team_id");
                if(teamId != null) {
                    if(!teamsCache.containsKey(teamId)) {
                        if(rs.getString("team_type").equals("club")) {
                            Country clubCountry = countryRepository.extractCountry(rs, "t");
                            
                            playerClub = new Club(teamId, rs.getString("team_name"), rs.getString("team_fullname"), clubCountry, rs.getString("team_preferred_jersey"), TacticalFormation.generateFormation(rs.getString("team_formation")), rs.getInt("club_rating"), rs.getDouble("club_money"));
                            teamsCache.put(teamId, playerClub);
                        }
                    }else{
                        playerClub = teamsCache.get(teamId);
                    }
                    
                    playerContract = new Contract(playerClub, rs.getDouble("contract_salary"), rs.getInt("contract_sign_date"), rs.getInt("contract_expire_date"), rs.getInt("contract_number"), Role.generateRole(rs.getString("contract_role")));
                }

                int playerId = rs.getInt("player_id");
                String playerName = rs.getString("player_name");
                String playerLastname = rs.getString("player_lastname");
                int rating = rs.getInt("player_rating");
                int potential = rs.getInt("player_potential");
                String playerPositions = rs.getString("player_positions");
                int playerBirthYear = rs.getInt("player_birth_year");
                int playerHeight = rs.getInt("player_height");
                int playerStamina = rs.getInt("player_stamina");

                Player player = new Player(playerId, playerName, playerLastname, rating, playerCountry, playerPositions, playerBirthYear, playerHeight, potential, playerContract, null, playerStamina);
                players.add(player);
            }
            
        } catch (SQLException sqlError) {
            System.out.println(" Error in PlayerRepository.loadAllPlayers(): " + sqlError.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }

        return players;
    }

    public Player loadPlayer() {
        return null;
    }

    private Player extractPlayer(ResultSet rs) {
        return null;
    }
}
