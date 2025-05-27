package com.ogifmsim.fmsimulator.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogifmsim.fmsimulator.dto.CountryDTO;
import com.ogifmsim.fmsimulator.model.country.Country;
import com.ogifmsim.fmsimulator.service.CountryService;

@RestController
@RequestMapping("api/football/countries")
public class CountryController {
    CountryService countryService = CountryService.getInstance();

    @GetMapping("/all")
    public List<CountryDTO> getAllCountries() {
        return countryService.getAllCountriesDTO();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getCountryById(@PathVariable String id) {
        CountryDTO countryDTO = countryService.getCountryByIdDTO(id);
        if(countryDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(countryDTO);
    }
}
