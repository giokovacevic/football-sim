package com.ogifmsim.fmsimulator.repository.preview;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ogifmsim.fmsimulator.config.DatabaseConnection;
import com.ogifmsim.fmsimulator.model.player.Player;
import com.ogifmsim.fmsimulator.model.team.Club;
import com.ogifmsim.fmsimulator.util.ResultSetMapper;

public class ClubRepository extends Repository<Club, String>{
    private static ClubRepository instance = null;

    private ClubRepository() {}

    public static ClubRepository getInstance() {
        if(instance == null) instance = new ClubRepository();
        return instance;
    }

    @Override
    public Club loadById(String id) {
        String query = "SELECT * FROM Club" +
        " INNER JOIN Team ON club_team_id = team_id" +
        " INNER JOIN Country team_country ON team_country_id = team_country.country_id" + 
        " WHERE club_team_id = ?;";

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(query)){
            statement.setString(1, id);
            try (ResultSet rs = statement.executeQuery()){
                if(rs.next()) {
                    Club club = ResultSetMapper.extractClub(rs, "");
                    if(club == null) return null;

                    List<Player> roster = loadRosterByClubId(club.getId());

                    for(Player player : roster) {
                        club.addPlayer(player);
                        player.getContract().setTeam(club);
                    }

                    return club; 
                }
            }
            return null;
        
        } catch (SQLException sqlError) {
            System.err.println(" Error in: " + getClass().getName() + ".loadById(): " + sqlError.getMessage());
            return null;
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    @Override
    public List<Club> loadAll() {
        String query = "SELECT * FROM Club" +
        " INNER JOIN Team ON club_team_id = team_id" +
        " INNER JOIN Country team_country ON team_country_id = team_country.country_id;";
        
        List<Club> clubs = new ArrayList<>();
        
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery()){
            
            while(rs.next()) {
                Club club = ResultSetMapper.extractClub(rs, "");
                if(club!=null) {
                    clubs.add(club);
                }
            }

            Map<String, List<Player>> rosters = loadAllRosters();

            for(Club club : clubs) {
                if(rosters.containsKey(club.getId())) {
                    for(Player player : rosters.get(club.getId())) {
                        club.addPlayer(player);
                        player.getContract().setTeam(club);
                    }
                }
            }
        } catch (SQLException sqlError) {
            System.err.println(" Error in: " + getClass().getName() + ".loadAll(): " + sqlError.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }

        return clubs;
    }

    public Map<String, List<Player>> loadAllRosters() {
        String query = "SELECT * FROM player" +
        " INNER JOIN country player_country on player_country_id = player_country.country_id" +
        " LEFT JOIN Contract on player_id = contract_player_id" + 
        " LEFT join club on contract_club_id = club_team_id" +
        " LEFT JOIN team on club_team_id = team_id" +
        " LEFT JOIN Country team_country on team_country_id = team_country.country_id;";

        Map<String, List<Player>> rosters = new HashMap<>();

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery()){
            while(rs.next()) {
                String clubId = rs.getString("contract_club_id");
                Player player = ResultSetMapper.extractPlayer(rs, "");
                if(player != null && clubId != null) {
                    if(!rosters.containsKey(clubId)) {
                        rosters.put(clubId, new ArrayList<>());
                    }

                    rosters.get(clubId).add(player);
                }

            }
        } catch (SQLException sqlError) {
            System.err.println(" Error in: " + getClass().getName() + ".loadRosters() " + sqlError.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }

        return rosters;
    }

    public List<Player> loadRosterByClubId(String clubId) {
        String query = "SELECT * FROM player" +
        " INNER JOIN country player_country on player_country_id = player_country.country_id" +
        " LEFT JOIN Contract on player_id = contract_player_id" + 
        " LEFT join club on contract_club_id = club_team_id" +
        " LEFT JOIN team on club_team_id = team_id" +
        " LEFT JOIN Country team_country on team_country_id = team_country.country_id" + 
        " WHERE contract_club_id = ?;";

        List<Player> roster = new ArrayList<>();

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(query)){
            statement.setString(1, clubId);
            
            try (ResultSet rs = statement.executeQuery()){
                while(rs.next()) {
                    Player player = ResultSetMapper.extractPlayer(rs, "");
                    if(player != null) {
                        roster.add(player);
                    }

                }
            }

        } catch (SQLException sqlError) {
            System.err.println(" Error in: " + getClass().getName() + ".loadRosterByClubId() " + sqlError.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }

        return roster;
    }
}
