package com.abdul.airlinemanager.city;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CityController {
    private final CityService cityService;

    /**
     * Get all cities or cities in a specific country depending on the
     * optional query
     * parameter 'country'.
     * @param country
     * @return
     */
    @GetMapping("")
    public List<City> getCities(@RequestParam(required = false) String country) {
        return country == null ? cityService.getAllCities() :
                cityService.getCitiesInCountry(country.toUpperCase());
    }
}
