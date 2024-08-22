package com.example.PostConnect.Controller;

import com.example.PostConnect.Models.Country;
import com.example.PostConnect.Models.Olympians;
import com.example.PostConnect.Repository.CountryRepository;
import com.example.PostConnect.Repository.OlympianRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OlympianController {

    @Autowired
    private OlympianRepository olympianRepository;

    @Autowired
    private CountryRepository countryRepository;

    private final Faker faker = new Faker();

    @PostMapping("/olympics/olympian/{countryCode}/register")
    public ResponseEntity<String> registerOlympian(@RequestBody Olympians olympians, @PathVariable String countryCode) {
        if (olympians.getOlympian_id() == null || olympians.getOlympian_id().isEmpty()) {
            String generatedId = faker.idNumber().valid();
            olympians.setOlympian_id(generatedId);
            System.out.println("Generated Olympian ID: " + generatedId);
        }

        Optional<Olympians> existingOlympian = olympianRepository.findById(olympians.getOlympian_id());
        if (existingOlympian.isPresent()) {
            return ResponseEntity.ok("Olympian already registered");
        }

        if (olympians.getFname() == null || olympians.getFname().isEmpty()) {
            olympians.setFname(faker.name().firstName());
        }

        if (olympians.getLname() == null || olympians.getLname().isEmpty()) {
            olympians.setLname(faker.name().lastName());
        }

        if (olympians.getDob() == null) {
            olympians.setDob(faker.date().birthday(18, 40));
        }

        if (olympians.getSex() == null || olympians.getSex().isEmpty()) {
            olympians.setSex(faker.options().option("Male", "Female"));
        }

        Optional<Country> country = countryRepository.findById(countryCode);

        if (country.isPresent()) {
            olympians.setCountry(country.get());
        } else {
            return ResponseEntity.badRequest().body("Country not found for code: " + countryCode);
        }

        olympianRepository.save(olympians);
        return ResponseEntity.ok(olympians.getFname() + " " + olympians.getLname() + " is successfully registered");
    }
}
