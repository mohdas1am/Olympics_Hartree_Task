package com.example.PostConnect.Repository;

import com.example.PostConnect.Models.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PostConnect.Models.Events;
import com.example.PostConnect.Models.Olympians;
import java.util.Optional;
import java.util.List;

public interface EventRegistrationRepository extends JpaRepository<EventRegistration,String> {
    Optional<EventRegistration> findByEventsAndOlympians(Events event, Olympians olympian);
    List<EventRegistration> findByEvents_Name(String eventName);

}
