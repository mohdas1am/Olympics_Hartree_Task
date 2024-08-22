package com.example.PostConnect.Controller;

import com.github.javafaker.Faker;
import com.example.PostConnect.Models.Country;
import com.example.PostConnect.Repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CountryController {

    @Autowired
    CountryRepository countryRepository;

    private final Faker faker = new Faker();

    @PostMapping("/olympics/country/register")
    public ResponseEntity<String> registerCountry(@RequestBody Country country) {
        if (country.getCountryCode() == null || country.getCountryCode().isEmpty()) {
            String generatedCountryCode = faker.address().countryCode();
            country.setCountryCode(generatedCountryCode);
        }

        Optional<Country> existingCountry = countryRepository.findById(country.getCountryCode());
        if (existingCountry.isPresent()) {
            return ResponseEntity.ok("Country already registered with code: " + country.getCountryCode());
        }

        if (country.getCountryName() == null || country.getCountryName().isEmpty()) {
            country.setCountryName(faker.country().name());
        }
        if (country.getPoints() == 0) {
            country.setPoints(faker.number().numberBetween(0, 0)); // Adjust as needed
        }
        if (country.getGold() == 0) {
            country.setGold(faker.number().numberBetween(0, 0));
        }
        if (country.getSilver() == 0) {
            country.setSilver(faker.number().numberBetween(0, 0));
        }
        if (country.getBronze() == 0) {
            country.setBronze(faker.number().numberBetween(0, 0));
        }

        countryRepository.save(country);
        return ResponseEntity.ok(country.getCountryName() + " is successfully registered with code: " + country.getCountryCode());
    }


    @GetMapping("/olympics/country/login/{countryCode}")
    public ResponseEntity<String> countryLogin(@PathVariable String countryCode) {
        Optional<Country> country = countryRepository.findById(countryCode);
        if (country.isPresent()) {
            return ResponseEntity.ok("Logged in as country: " + country.get().getCountryName());
        }
        return ResponseEntity.badRequest().body("No Country found");
    }
}
