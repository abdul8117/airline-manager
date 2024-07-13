package com.abdul.airlinemanager.airport;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public void addAirport(Airport airport) {
        airportRepository.save(airport);
    }

    public List<String> getAllCountries() {
        return airportRepository.findDistinctByOrderByCountry();
    }

    public List<String> getAllCitiesInCountry(String country) {
        return airportRepository.findByOrderByCity(country);
    }

    public List<Airport> getAirportsByCityId(Integer id) {
        return airportRepository.findByCity_CityIdOrderByCity_Name(id);
    }

    public Airport getAirportById(Long airportId) throws Exception {
        return airportRepository.findById(airportId).orElseThrow(() -> new Exception("Airport not found"));
    }
}
