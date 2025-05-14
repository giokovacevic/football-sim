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
import com.ogifmsim.fmsimulator.model.country.Country;

public class CountryService {

    private static CountryService instance = null;
    private static Map<String, Country> countries = null;

    private CountryService() {
        if(countries == null) countries = loadAllCountries("db_countries.csv");
    }

    public static CountryService getInstance() {
        if(instance == null) {
            instance = new CountryService();
        }
        return instance;
    }

    public List<Country> getAllCountries() {
        if(countries == null) {
            countries = loadAllCountries("db_countries.csv");
        }
        return new ArrayList<>(countries.values());
    }

    public Country getCountryById(String countryId) {
        if(countries == null) {
            countries = loadAllCountries("db_countries.csv");
        }
        if(countries.containsKey(countryId)) return countries.get(countryId);
        return null;
    }
     
    private Map<String, Country> loadAllCountries(String filename) {
        Map<String, Country> countries = new HashMap<>();
        String lastCountryLoadedId = "";
        
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                String loadedCountryString = sc.nextLine();
                String[] countryArray = loadedCountryString.split(",");
                Country country = new Country(countryArray[1], countryArray[2]);
                countries.put(country.getId(), country);
                lastCountryLoadedId = country.getId();
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(" Error: Country Loader. Last Country loaded ID:  " + lastCountryLoadedId);
        }

        return countries;
    }

    public void insertAllCountries() { // TODO: Move to CountryRepository and add loadAllCountries with sql
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
    }
}
