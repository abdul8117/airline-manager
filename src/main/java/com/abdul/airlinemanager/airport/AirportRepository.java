package com.abdul.airlinemanager.airport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    @Query("SELECT DISTINCT a.country FROM Airport a ORDER BY a.country ASC")
    List<String> findDistinctByOrderByCountry();

    @Query("SELECT DISTINCT a.city FROM Airport a WHERE LOWER(a.country) = ?1" +
            " " + "ORDER BY a.city ASC")
    List<String> findByOrderByCity(String country);

    List<Airport> findByCity_CityIdOrderByCity_Name(Integer cityId);

    Airport findByAirportId(Long airportId);
}
