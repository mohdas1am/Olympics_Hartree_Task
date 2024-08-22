package com.example.PostConnect.Controller;

import com.example.PostConnect.Models.Country;
import com.example.PostConnect.Models.EventRegistration;
import com.example.PostConnect.Models.Events;
import com.example.PostConnect.Models.Olympians;
import com.example.PostConnect.Repository.CountryRepository;
import com.example.PostConnect.Repository.EventRegistrationRepository;
import com.example.PostConnect.Repository.EventRepository;
import com.example.PostConnect.Repository.OlympianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EventRegistrationController {

    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private EventRepository eventsRepository;

    @Autowired
    private OlympianRepository olympiansRepository;

    @PostMapping("/olympics/event/{countryCode}/{event_id}/{olympian_id}/eventregister")
    public ResponseEntity<String> registerEvent(@RequestBody EventRegistration eventRegistration,
                                                @PathVariable String countryCode,
                                                @PathVariable String event_id,
                                                @PathVariable String olympian_id) {

        Optional<Country> optionalCountry = countryRepository.findById(countryCode);
        if (!optionalCountry.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid country code");
        }


        Optional<Events> optionalEvent = eventsRepository.findById(event_id);
        if (!optionalEvent.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid event ID");
        }


        Optional<Olympians> optionalOlympian = olympiansRepository.findById(olympian_id);
        if (!optionalOlympian.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Olympian ID");
        }


        Events event = optionalEvent.get();
        Olympians olympian = optionalOlympian.get();
        Country country = optionalCountry.get();


        if (!olympian.getCountry().getCountryCode().equals(countryCode)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Olympian does not belong to the specified country");
        }


        Optional<EventRegistration> existingRegistration = eventRegistrationRepository.findByEventsAndOlympians(event, olympian);
        if (existingRegistration.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Event " +event.getName()+" already registered for this Olympian");
        }

        eventRegistration.setEvents(event);
        eventRegistration.setOlympians(olympian);
        eventRegistration.setCountry(country);

        if (eventRegistration.getRegistrationId() == null || eventRegistration.getRegistrationId().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration ID must be provided");
        }

        eventRegistrationRepository.save(eventRegistration);
        return ResponseEntity.ok("Event registered successfully");
    }
}
