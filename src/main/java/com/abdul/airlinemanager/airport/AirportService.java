package com.abdul.airlinemanager.airport;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public void addAirport(Airport airport) {
        airportRepository.save(airport);
    }

    public List<Airport> getAirportsByCityId(Long id) {
        return airportRepository.findByCity_CityIdOrderByCity_Name(id);
    }

    public Airport getAirportById(Long airportId) throws Exception {
        return airportRepository.findById(airportId).orElseThrow(() -> new Exception("Airport not found"));
    }
}
