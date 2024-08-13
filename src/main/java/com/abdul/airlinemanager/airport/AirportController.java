package com.abdul.airlinemanager.airport;

import com.abdul.airlinemanager.city.City;
import com.abdul.airlinemanager.city.CityService;
import com.abdul.airlinemanager.country.Country;
import com.abdul.airlinemanager.country.CountryService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airports")
@CrossOrigin(origins = "http://localhost:5173")
public class AirportController {

    private final AirportService airportService;
    private final CityService cityService;
    private final CountryService countryService;

    @GetMapping("/id/{id}")
    public Airport getAirportById(@PathVariable Long id) throws Exception {
        return airportService.getAirportById(id);
    }

    @GetMapping("")
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping("/cities/{id}")
    public List<Airport> getAirportsByCityId(@PathVariable Integer id) {
        return airportService.getAirportsByCityId(id);
    }


    @PostConstruct
    public void init() {
        String filePath = "datasets/GlobalAirportDatabase.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(":");

                if (columns.length >= 5) {
                    String icaoCode = columns[0];
                    String iataCode = columns[1];
                    String airportName = columns[2];
                    String city = columns[3];
                    String country = columns[4];

                    if (icaoCode.equals("N/A") || iataCode.equals("N/A") || airportName.equals("N/A")
                            || city.equals("N/A") || country.equals("N/A")) {
                        continue;
                    }

                    Country countryObj =
                            countryService.findByName(country).orElseGet(
                                    () -> countryService.addCountry(new Country(country))
                            );

                    City cityObj = cityService.findByNameAndCountry(city,
                            countryObj).orElseGet(
                                    () -> cityService.addCity(City.builder()
                                            .name(city).country(countryObj).build())
                            );

                    Integer gateCost = new Random().nextInt(299,5000) + 1;

                    Airport airport = Airport.builder()
                            .name(airportName)
                            .iataCode(iataCode)
                            .icaoCode(icaoCode)
                            .weeklyGateCost(gateCost)
                            .city(cityObj)
                            .country(countryObj)
                            .build();

                    airportService.addAirport(airport);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
