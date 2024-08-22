package com.example.PostConnect.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="Events")
public class Events {
    @Id
    private String  event_id;
    private String name;
    private Date date;
    private String location;
    private boolean done;
    public Events() {
    }


    @Override
    public String toString() {
        return "Events{" +
                "event_id='" + event_id + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", done=" + done +
                '}';
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEventId(String eventId) {
        this.event_id = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
