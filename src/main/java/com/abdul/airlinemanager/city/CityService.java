package com.abdul.airlinemanager.city;

import com.abdul.airlinemanager.country.Country;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City addCity(City city) {
        return cityRepository.save(city);
    }

    public Optional<City> findByNameAndCountry(String name, Country country) {
        return cityRepository.findByNameAndCountry(name, country);
    }

    public List<City> getCitiesInCountry(String country) {
        return cityRepository.findByCountryName(country);
    }
}
