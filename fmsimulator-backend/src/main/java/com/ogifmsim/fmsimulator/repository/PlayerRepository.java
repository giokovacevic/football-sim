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
import com.ogifmsim.fmsimulator.repository.preview.CountryRepository;
import com.ogifmsim.fmsimulator.repository.preview.Repository;
import com.ogifmsim.fmsimulator.util.ResultSetMapper;

public class PlayerRepository extends Repository<Player, Integer>{
    private static PlayerRepository instance = null;

    private static CountryRepository countryRepository = CountryRepository.getInstance();

    private PlayerRepository() { }

    public static PlayerRepository getInstance() {
        if(instance == null) instance = new PlayerRepository();
        return instance;
    }

    @Override
    public Player loadById(Integer id) {
        return null;
    }
    
    @Override
    public List<Player> loadAll() {
        List<Player> players = new ArrayList<>();

        String query = "SELECT * FROM player" +
        " INNER JOIN country player_country on player_country_id = player_country.country_id" +
        " LEFT JOIN Contract on player_id = contract_player_id" + 
        " LEFT join club on contract_club_id = club_team_id" +
        " LEFT JOIN team on club_team_id = team_id" +
        " LEFT JOIN Country team_country on team_country_id = team_country.country_id;";

        try(PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery()) {

            while(rs.next()) {
                Player player = ResultSetMapper.extractPlayer(rs, "");
                if(player != null) players.add(player);
            }
            
        } catch (SQLException sqlError) {
            System.out.println(" Error in PlayerRepository.loadAllPlayers(): " + sqlError.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }

        return players;
    }
}
