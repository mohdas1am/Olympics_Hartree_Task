package com.example.PostConnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PostConnect.Models.Events;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Events,String> {
    Optional<Events> findByName(String name);
}
