package com.abdul.airlinemanager.airport;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    List<Airport> findByCity_CityIdOrderByCity_Name(Long city_cityId);

    Airport findByAirportId(Long airportId);
}
