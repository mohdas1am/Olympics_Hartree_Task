package com.example.PostConnect.Controller;

import com.example.PostConnect.Models.Events;
import com.example.PostConnect.Repository.EventRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class EventController {

    @Autowired
    EventRepository erepo;

    private final Faker faker = new Faker();

    @PostMapping("olympics/addEvent")
    public ResponseEntity<String> addEvent(@RequestBody Events events) {
        if (events.getEvent_id() == null || events.getEvent_id().isEmpty()) {
            events.setEvent_id(faker.idNumber().valid());
        }

        if (events.getName() == null || events.getName().isEmpty()) {
            events.setName(faker.esports().event());
        }

        if (events.getDate() == null) {
            events.setDate(faker.date().future(30, java.util.concurrent.TimeUnit.DAYS));
        }

        if (events.getLocation() == null || events.getLocation().isEmpty()) {
            events.setLocation(faker.address().fullAddress());
        }

        erepo.save(events);

        return ResponseEntity.ok("Event added successfully");
    }

    @GetMapping("/getEvents")
    public List<Events> showEvents() {
        return erepo.findAll();
    }

    @GetMapping("/getEventById/{id}")
    public ResponseEntity<Events> getById(@PathVariable String id) {
        return erepo.findById(id)
                .map(event -> ResponseEntity.ok().body(event))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
