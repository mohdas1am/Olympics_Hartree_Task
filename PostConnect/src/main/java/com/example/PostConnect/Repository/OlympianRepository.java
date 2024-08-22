package com.example.PostConnect.Repository;

import com.example.PostConnect.Models.Olympians;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OlympianRepository extends JpaRepository<Olympians,String> {
}
