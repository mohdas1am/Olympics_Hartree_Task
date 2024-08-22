package com.example.PostConnect.Repository;

import com.example.PostConnect.Models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,String> {
}
