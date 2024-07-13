package com.abdul.airlinemanager.country;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    Optional<Country> findByName(String name);

}
