package com.abdul.airlinemanager.city;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@CrossOrigin(origins = "http://localhost:5173")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("")
    public List<City> getCities(@RequestParam(required = false) String country) {
        return country == null ? cityService.getAllCities() :
                cityService.getCitiesInCountry(country.toUpperCase());
    }

}
