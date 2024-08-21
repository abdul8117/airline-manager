package com.abdul.airlinemanager.country;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    public Optional<Country> findByName(String name) {
        return countryRepository.findByName(name);
    }
}
