package com.example.PostConnect.Repository;

import com.example.PostConnect.Models.Country;
import com.example.PostConnect.Models.MedalTally;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedalTallyRepository extends JpaRepository<MedalTally, Country> {
}
