package com.example.PostConnect.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.security.cert.CertPath;

@Entity
@Data
@Table(name = "Registration")
public class EventRegistration {
    @Id
    private String registrationId;
    @ManyToOne
    @JoinColumn(name="countryCode",referencedColumnName = "countryCode")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "event_id",referencedColumnName = "event_id")
    private Events events;

    @ManyToOne
    @JoinColumn(name = "olympian_id",referencedColumnName = "olympian_id")
    private Olympians olympians;

    public EventRegistration() {
    }

    @Override
    public String toString() {
        return "EventRegistration{" +
                "registrationId='" + registrationId + '\'' +
                ", country=" + country +
                ", events=" + events +
                ", olympians=" + olympians +
                '}';
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    public Olympians getOlympians() {
        return olympians;
    }

    public void setOlympians(Olympians olympians) {
        this.olympians = olympians;
    }
}
