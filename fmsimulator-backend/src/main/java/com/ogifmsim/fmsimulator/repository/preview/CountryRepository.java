package com.ogifmsim.fmsimulator.repository.preview;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;
import com.ogifmsim.fmsimulator.config.DatabaseConnection;
import com.ogifmsim.fmsimulator.model.country.Country;
import com.ogifmsim.fmsimulator.util.ResultSetMapper;

public class CountryRepository extends Repository<Country, String>{
    private static CountryRepository instance = null;

    private CountryRepository() { }

    public static CountryRepository getInstance() {
        if(instance == null) instance = new CountryRepository();
        return instance;
    }

    @Override
    public Country loadById(String id) {
        String query = "SELECT * FROM Country c WHERE c.country_id = ?;";
        Country country = null;

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(query)){
            statement.setString(1, id); 
            try(ResultSet rs = statement.executeQuery()){
                if(rs.next()) return ResultSetMapper.extractCountry(rs, "c");  
            }
        } catch (SQLException sqlError) {
            System.out.println(" Error in: CountryRepository.loadCountryById(): " + sqlError.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }

        return country;
    }

    @Override
    public List<Country> loadAll() {
        String query = "SELECT * FROM Country c;";
        List<Country> countries = new ArrayList<>();
        
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(query); 
             ResultSet rs = statement.executeQuery()){
            
            while(rs.next()) {
                Country country = ResultSetMapper.extractCountry(rs, "c");
                if(country!=null) countries.add(country);
            }
        } catch (SQLException sqlError) {
            System.out.println(" Error in: CountryRepository.loadAllCountries(): " + sqlError.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }

        return countries;
    }  
}
