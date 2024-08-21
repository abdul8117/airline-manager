package com.abdul.airlinemanager.country;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/countries")
@CrossOrigin(origins = "http://localhost:5173")
public class CountryController {
    private final CountryService countryService;

    /**
     * Returns all countries in the database.
     * @return List of all countries.
     */
    @GetMapping("")
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

}
