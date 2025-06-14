package com.ogifmsim.fmsimulator.repository.preview;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ogifmsim.fmsimulator.model.country.Country;
import com.ogifmsim.fmsimulator.model.team.Club;

public class ClubRepository {
    private static ClubRepository instance = null;

    private CountryRepository countryRepository = CountryRepository.getInstance();

    private ClubRepository() {}

    public static ClubRepository getInstance() {
        if(instance == null) instance = new ClubRepository();
        return instance;
    }

    public Club extractClub(ResultSet rs, String alias) {
        String prefix = alias.equals("") ? "" : alias + ".";
        try {
            String id= rs.getString(prefix + "club_team_id");
            if(id == null) return null;

            Country country = countryRepository.extract(rs, "t");


        } catch (SQLException sqlError) {
            System.out.println(" Error in ClubRepository.extractClub(): " + sqlError.getMessage());
            return null;
        }
        return null;
    }
}
