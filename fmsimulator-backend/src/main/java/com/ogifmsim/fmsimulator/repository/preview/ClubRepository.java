package com.ogifmsim.fmsimulator.repository.preview;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ogifmsim.fmsimulator.model.country.Country;
import com.ogifmsim.fmsimulator.model.team.Club;
import com.ogifmsim.fmsimulator.util.ResultSetMapper;

public class ClubRepository {
    private static ClubRepository instance = null;

    private CountryRepository countryRepository = CountryRepository.getInstance();

    private ClubRepository() {}

    public static ClubRepository getInstance() {
        if(instance == null) instance = new ClubRepository();
        return instance;
    }
}
