package com.ogifmsim.fmsimulator.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ogifmsim.fmsimulator.config.DatabaseConnection;
import com.ogifmsim.fmsimulator.dto.CountryDTO;
import com.ogifmsim.fmsimulator.model.country.Country;
import com.ogifmsim.fmsimulator.repository.CountryRepository;

public class CountryService {
    private static CountryService instance = null;

    private static CountryRepository countryRepository = CountryRepository.getInstance();

    private CountryService() {}

    public static CountryService getInstance() {
        if(instance == null) {
            instance = new CountryService();
        }
        return instance;
    }

    public List<Country> getAllCountries() {
        return countryRepository.loadAllCountries();
    }

    public List<CountryDTO> getAllCountriesDTO() {
        List<CountryDTO> countriesDTO = new ArrayList<>();
        for(Country country : getAllCountries()) {
            countriesDTO.add(new CountryDTO(country));
        }
        return countriesDTO;
    }

    public Country getCountryById(String countryId) {
        return countryRepository.loadCountryById(countryId);
    }

    public CountryDTO getCountryByIdDTO(String countryId) {
        Country country = getCountryById(countryId);
        if(country != null) return new CountryDTO(getCountryById(countryId));
        return null;
    }

    /*public void insertAllCountries() { // TODO: Move to CountryRepository and add loadAllCountries with sql
        String query = "INSERT INTO Country(`country_id`, `country_name`) VALUES (?, ?)";
        try(PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(query);) {
            countries.forEach((key, value) -> {
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
    }*/
}
