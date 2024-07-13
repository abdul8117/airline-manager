package com.abdul.airlinemanager.city;

import com.abdul.airlinemanager.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByNameAndCountry(String name, Country country);

    List<City> findByCountryName(String country);

}
