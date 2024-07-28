package com.abdul.airlinemanager.route;

import com.abdul.airlinemanager.airport.Airport;
import com.abdul.airlinemanager.airport.AirportService;
import com.abdul.airlinemanager.auth.AuthenticationService;
import com.abdul.airlinemanager.player.Player;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {

    private static final Logger log =
            LoggerFactory.getLogger(AuthenticationService.class);

    private final AirportService airportService;
    private final RouteRepository routeRepository;
    private final RestClient restClient = RestClient.builder()
            .baseUrl("https://airportgap.com/api").build();

    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    public void addRoute(Long destinationAirportId, Player player) throws Exception {
        Airport destinationAirport =
                airportService.getAirportById(destinationAirportId);

        Integer distance =
                getDistanceBetweenHubAndDestination(player.getAirport().getIataCode(), destinationAirport.getIataCode());
        Integer flightTime = calculateFlightTime(distance);

        Route newRoute = Route.builder()
                .hubAirport(player.getAirport())
                .destinationAirport(airportService.getAirportById(destinationAirportId))
                .distance(distance)
                .totalFlightTime(flightTime)
                .player(player)
                .build();

        routeRepository.save(newRoute);
    }

    /**
     * Calculate the total flight time for a route to and from the destination.
     * Assumes 900 km/h average speed and 30 minutes turnaround time.
     * @param distance Distance in kilometers
     * @return Total flight time in minutes
     */
    private Integer calculateFlightTime(Integer distance) {
        return ((distance / 900) * 60 * 2) + 30;
    }

    /**
     * Get the distance between the hub airport and the destination airport
     * in kilometers.
     * Calls the Airport Gap API to get the distance.
     * @param originAirportCode Origin airport IATA code
     * @param destinationAirportCode Destination airport IATA code
     * @return Distance in kilometers
     */
    private Integer getDistanceBetweenHubAndDestination(String originAirportCode,
                                                        String destinationAirportCode) {
        String uriWithParams = "/airports/distance?from=" + originAirportCode +
                "&to=" + destinationAirportCode;

        try {
            log.info("Sending API request to Airport Gap API: {}", uriWithParams);

            AirportGapDistanceDto response = restClient.post()
                    .uri(uriWithParams)
                    .retrieve()
                    .body(AirportGapDistanceDto.class);

            log.info("Received response from Airport Gap API: {}", response.getData());

            if (response.getData() != null && response.getData().getAttributes() != null) {
                return response.getData().getAttributes().getKilometers().intValue();
            } else {
                throw new RuntimeException("Invalid response from Airport Gap API");
            }
        } catch (Exception e) {
            log.error("Error getting distance between airports", e);
            throw new RuntimeException("Error getting distance between airports", e);
        }
    }
}
