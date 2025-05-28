package com.ogifmsim.fmsimulator.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ogifmsim.fmsimulator.config.DatabaseConnection;
import com.ogifmsim.fmsimulator.dto.PlayerDTO;
import com.ogifmsim.fmsimulator.model.enums.Role;
import com.ogifmsim.fmsimulator.model.player.Contract;
import com.ogifmsim.fmsimulator.model.player.Player;
import com.ogifmsim.fmsimulator.model.team.Club;
import com.ogifmsim.fmsimulator.util.PlayerComparator;

public class PlayerService {
    private final static String CSV_URL = "db_players.csv";

    private static PlayerService instance = null;
    private static CountryService countryService = CountryService.getInstance();
    private static ClubService clubService = ClubService.getInstance();
    private static List<Player> players = null;

    private PlayerService() {
        if(players == null) players = loadAllPlayers(CSV_URL);
    }

    public static PlayerService getInstance() {
        if(instance == null) {
            instance = new PlayerService();
        }
        return instance;
    }

    public List<Player> getAllPlayers() {
        if(players == null) {
            players = loadAllPlayers(CSV_URL);
        }
        return players;
    }

    public List<PlayerDTO> getAllPlayersDTO() {
        List<PlayerDTO> playersDTO = new ArrayList<>();
        for(Player player : getAllPlayers()) {
            playersDTO.add(new PlayerDTO(player));
        }
        return playersDTO;
    }

    public Map<String, Object> getAllPlayersByPageDTO(int pageNumber, int limit, String sortingKey, String sortingOrder, String sortingOrientation) {
        Map<String, Object> page = new HashMap<>();
        List<Player> playersCopy = new ArrayList<>(getAllPlayers());
        if(sortingKey != null && sortingOrder != null) {
            playersCopy.sort((p1, p2) -> {
                int cmp = PlayerComparator.compare(p1, p2, sortingKey, sortingOrientation);
                return sortingOrder.equalsIgnoreCase("desc") ? -cmp : cmp;
            });
        }
        
        int totalPages = (int) Math.ceil((double)playersCopy.size() / limit);
        if(pageNumber < 0 || pageNumber >= totalPages) return null;
        List<PlayerDTO> playersDTO = new ArrayList<>();
        int startIndex = pageNumber * limit;
        int endIndex = Math.min(startIndex + limit, playersCopy.size());
        for(int i=startIndex; i<endIndex; i++) {
            playersDTO.add(new PlayerDTO(playersCopy.get(i)));
        }
        page.put("players", playersDTO);
        page.put("totalPlayers", playersCopy.size());
        page.put("page", pageNumber);
        page.put("totalPages", totalPages);

        return page;
    }
     
    private List<Player> loadAllPlayers(String filename) {
        List<Player> playerList = new ArrayList<>();
        
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                String loadedPlayerString = sc.nextLine();
                String[] playerArray = loadedPlayerString.split(",");
                
                Club club = clubService.getClubById(playerArray[13]);
                
                Player playerNew = new Player(Integer.parseInt(playerArray[0]), playerArray[6], playerArray[5],
                        Integer.parseInt(playerArray[8]),
                        countryService.getCountryById(playerArray[3]), playerArray[7],
                        (2025 - Integer.parseInt(playerArray[9])), Integer.parseInt(playerArray[10]),
                        Integer.parseInt(playerArray[11]),
                        new Contract(club, Double.parseDouble(playerArray[14]), 2025,
                        2025 + Integer.parseInt(playerArray[15]), Integer.parseInt(playerArray[4]),
                        Role.generateRole(playerArray[16])),null, 100);
                playerList.add(playerNew);

                if(club != null) {
                    club.addPlayer(playerNew);
                    club.getRoster().getLineup().save();
                }
            }

            sc.close();
        } catch (FileNotFoundException | NumberFormatException e) {
            System.out.println(" Error: Player Loader");
        }

        return playerList;
    }

    public void insertAllPlayers() { // TODO: Move to PlayerRepository and add loadAllPlayers with sql
        String query = "INSERT INTO Player(`player_id`, `player_name`, `player_lastname`, `player_country_id`, `player_positions`, `player_rating`, `player_potential`, `player_birth_year`, `player_height`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; // , ?, ?, ?, ?, ?, ?
        try(PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(query)) { // , `player_contract_club_id`, `player_contract_salary`, `player_contract_sign_date`, `player_contract_expire_date`, `player_contract_role`, `player_contract_number`
                players.forEach(player -> {
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
