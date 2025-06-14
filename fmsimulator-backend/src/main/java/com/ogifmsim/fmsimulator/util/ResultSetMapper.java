package com.ogifmsim.fmsimulator.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ogifmsim.fmsimulator.model.country.Country;
import com.ogifmsim.fmsimulator.model.enums.Role;
import com.ogifmsim.fmsimulator.model.enums.TacticalFormation;
import com.ogifmsim.fmsimulator.model.player.Contract;
import com.ogifmsim.fmsimulator.model.player.Player;
import com.ogifmsim.fmsimulator.model.team.Club;

public class ResultSetMapper {

    public static Country extractCountry(ResultSet rs, String alias) {
        String prefix = alias.equals("") ? "" :  alias + ".";
        try {
            String id = rs.getString(prefix + "country_id");
            if(id == null) return null;
            
            String name = rs.getString(prefix + "country_name");

            return new Country(id, name);
        } catch (SQLException sqlError) {
            System.out.println(" Error in: ResultSetMapper.extractCountry(): " + sqlError.getMessage());
            return null;
        }
    }

    public static Player extractPlayer(ResultSet rs, String alias) {
        String prefix = alias.equals("") ? "" : alias + ".";
        try {
            int id = rs.getInt(prefix + "player_id");
            if(rs.wasNull()) return null;

            Country country = ResultSetMapper.extractCountry(rs, "player_country");
            if(country == null) return null;

            String name = rs.getString(prefix + "player_name");
            String lastname = rs.getString(prefix + "player_lastname");
            int rating = rs.getInt(prefix + "player_rating");
            int potential = rs.getInt(prefix + "player_potential");
            String positions = rs.getString(prefix + "player_positions");
            int birthYear = rs.getInt(prefix + "player_birth_year");
            int height = rs.getInt(prefix + "player_height");
            int stamina = rs.getInt(prefix + "player_stamina");
            
            Contract contract = extractContract(rs, "");

            return new Player(id, name, lastname, rating, country, positions, birthYear, height, potential, null, null, stamina);
        } catch (SQLException sqlError) {
            System.out.println(" Error in ResultSetMapper.extractPlayer(): " + sqlError.getMessage());
            return null;
        }
    }

    public static Contract extractContract(ResultSet rs, String alias) {
        String prefix = alias.equals("") ? "" : alias + ".";

        try {
            int id = rs.getInt(prefix + "contract_player_id");
            if(rs.wasNull()) return null;

            Club club = extractClub(rs, alias);
            if(club == null) return null;

            int signDate = rs.getInt(prefix + "contract_sign_date");
            int expireDate = rs.getInt(prefix + "contract_expire_date");
            double salary = rs.getDouble(prefix + "contract_salary");
            int number = rs.getInt(prefix + "contract_number");
            Role role = Role.generateRole(rs.getString(prefix + "contract_role"));

            return new Contract(club, salary, signDate, expireDate, number, role);
        } catch (SQLException sqlError) {
            System.out.println(" Error in ResultSetMapper.extractContract(): " + sqlError.getMessage());
            return null;
        }
    }

    public static Club extractClub(ResultSet rs, String alias) {
        String prefix = alias.equals("") ? "" : alias + ".";
        try {
            String id = rs.getString(prefix + "club_team_id");
            if(id == null) return null;

            Country country = ResultSetMapper.extractCountry(rs, "team_country");
            if(country == null) return null;

            String name = rs.getString(prefix + "team_name");
            String fullname = rs.getString(prefix + "team_fullname");
            String preferred_jersey = rs.getString(prefix + "team_preferred_jersey");
            TacticalFormation formation = TacticalFormation.generateFormation(rs.getString(prefix + "team_formation"));
            int rating = rs.getInt(prefix + "club_rating");
            double money = rs.getDouble(prefix + "club_money");

            return new Club(id, name, fullname, country, preferred_jersey, formation, rating, money);
        } catch (SQLException sqlError) {
            System.out.println(" Error in ResultSetMapper.extractClub(): " + sqlError.getMessage());
            return null;
        }
    }
}
