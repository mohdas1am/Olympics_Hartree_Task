package com.example.PostConnect.Controller;

import com.example.PostConnect.Models.Country;
import com.example.PostConnect.Models.EventRegistration;
import com.example.PostConnect.Models.Olympians;
import com.example.PostConnect.Models.Events;
import com.example.PostConnect.Models.Medalist;
import com.example.PostConnect.Repository.CountryRepository;
import com.example.PostConnect.Repository.EventRegistrationRepository;
import com.example.PostConnect.Repository.EventRepository;
import com.example.PostConnect.Repository.OlympianRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class EventRunController {

    private static final Logger logger = LoggerFactory.getLogger(EventRunController.class);

    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    @Autowired
    private OlympianRepository olympiansRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private EventRepository eventsRepository;

    @PostMapping("/olympics/admin/run/{eventName}")
    public ResponseEntity<Map<String, Object>> runEvent(@PathVariable String eventName) {
        Optional<Events> optionalEvent = eventsRepository.findByName(eventName);
        if (!optionalEvent.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Event "+eventName+"not found"));
        }

        Events event = optionalEvent.get();

        if (event.isDone()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Event " +event.getName()+" has already been run"));
        }

        List<EventRegistration> registrations = eventRegistrationRepository.findByEvents_Name(eventName);

        if (registrations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "No registrations for this event "+event.getName()));
        }

        Collections.shuffle(registrations);

        EventRegistration goldWinner = registrations.size() > 0 ? registrations.get(0) : null;
        EventRegistration silverWinner = registrations.size() > 1 ? registrations.get(1) : null;
        EventRegistration bronzeWinner = registrations.size() > 2 ? registrations.get(2) : null;

        if (goldWinner != null) {
            updateOlympianMedals(goldWinner.getOlympians(), 1, 0, 0);
            updateCountryPosition(goldWinner.getCountry(),1,0,0);
        }
        if (silverWinner != null) {
            updateOlympianMedals(silverWinner.getOlympians(), 0, 1, 0);
            updateCountryPosition(silverWinner.getCountry(),0,1,0);
        }
        if (bronzeWinner != null) {
            updateOlympianMedals(bronzeWinner.getOlympians(), 0, 0, 1);
            updateCountryPosition(bronzeWinner.getCountry(),0,0,1);
        }

        event.setDone(true);
        eventsRepository.save(event);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Event successfully completed and winners updated");
        List<Medalist> winners = new ArrayList<>();
        if (goldWinner != null) winners.add(createMedalist(goldWinner));
        if (silverWinner != null) winners.add(createMedalist(silverWinner));
        if (bronzeWinner != null) winners.add(createMedalist(bronzeWinner));
        response.put("winners", winners);

        return ResponseEntity.ok(response);
    }

    private void updateOlympianMedals(Olympians olympian, int gold, int silver, int bronze) {
        if (olympian == null) return;

        olympian.setGold(olympian.getGold() + gold);
        olympian.setSilver(olympian.getSilver() + silver);
        olympian.setBronze(olympian.getBronze() + bronze);

        logger.debug("Updating Olympian: " + olympian.getOlympian_id() +
                " Gold: " + olympian.getGold() +
                " Silver: " + olympian.getSilver() +
                " Bronze: " + olympian.getBronze());

        olympiansRepository.save(olympian);
    }

    private void updateCountryPosition(Country country,int gold, int silver, int bronze) {
        if (country == null) return;

        country.setGold(country.getGold()+gold);
        country.setSilver(country.getSilver()+silver);
        country.setBronze(country.getBronze()+bronze);

        int newPoints = country.getPoints()+ 3 * gold + 2 * silver + bronze;

        logger.debug("Updating Country: " + country.getCountryCode() +
                " Gold: " + gold +
                " Silver: " + silver +
                " Bronze: " + bronze +
                " New Points: " + newPoints);

        country.setPoints(newPoints);
        countryRepository.save(country);
    }

    private Medalist createMedalist(EventRegistration registration) {
        Olympians olympian = registration.getOlympians();
        if (olympian == null) {
            return null;
        }

        Medalist medalist = new Medalist();
        medalist.setOlympianId(olympian.getOlympian_id());
        medalist.setName(olympian.getFname() + " " + olympian.getLname());
        medalist.setCountryCode(olympian.getCountry() != null ? olympian.getCountry().getCountryCode() : "Unknown");
        medalist.setGold(olympian.getGold());
        medalist.setSilver(olympian.getSilver());
        medalist.setBronze(olympian.getBronze());

        return medalist;
    }
}
