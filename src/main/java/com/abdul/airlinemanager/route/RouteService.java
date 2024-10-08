package com.abdul.airlinemanager.route;

import com.abdul.airlinemanager.airport.Airport;
import com.abdul.airlinemanager.airport.AirportService;
import com.abdul.airlinemanager.auth.AuthenticationService;
import com.abdul.airlinemanager.fleet.AircraftFleet;
import com.abdul.airlinemanager.fleet.AircraftFleetDto;
import com.abdul.airlinemanager.fleet.AircraftFleetRepository;
import com.abdul.airlinemanager.player.Player;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {
    private static final Logger log =
            LoggerFactory.getLogger(AuthenticationService.class);

    private final AirportService airportService;
    private final AircraftFleetRepository aircraftFleetRepository;
    private final RouteRepository routeRepository;
    private final RouteAircraftRepository routeAircraftRepository;
    private final RestClient restClient = RestClient.builder()
            .baseUrl("https://airportgap.com/api").build();


    /**
     * Adds a new route to the database.
     * @param destinationAirportId
     * @param player
     * @throws Exception If the destination airport is not found
     */
    public void addRoute(Long destinationAirportId, Player player) throws Exception {
        Airport destinationAirport =
                airportService.getAirportById(destinationAirportId);

        Integer distance =
                getDistanceBetweenHubAndDestination(player.getAirport().getIataCode(), destinationAirport.getIataCode());
        Integer flightTime = calculateFlightTime(distance);
        Integer demand = ((int) (Math.random() * 1000)) + 400;

        Route newRoute = Route.builder()
                .hubAirport(player.getAirport())
                .destinationAirport(airportService.getAirportById(destinationAirportId))
                .distance(distance)
                .totalFlightTime(flightTime)
                .paxDemand(demand)
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

    @Transactional
    public void scheduleFlights(
            Long routeId,
            Long aircraftFleetId,
            Integer weeklyFrequency,
            Player player
    ) {
        // get the flight time of the route
        Route route = routeRepository.findByRouteId(routeId);
        AircraftFleet aircraftInFleet =
                aircraftFleetRepository.findByAircraftFleetId(aircraftFleetId);
        Integer flightTime = route.getTotalFlightTime();

        // calculate the total time available in a week for the aircraft
        Integer hoursAvailable = calculateHoursAvailable(aircraftFleetId);
        if (hoursAvailable - flightTime * weeklyFrequency < 0) {
            throw new RuntimeException("Aircraft does not have enough time to " +
                    "fly this route " + weeklyFrequency + " times per week");
        }

        // check if there are already flights scheduled for this route
        if (areFlightsAlreadyScheduled(route, aircraftFleetId)) {
            // update the current route with the new frequency
            Integer currentFrequency =
                    routeAircraftRepository.findByRouteAndAircraftId(route,
                            aircraftFleetRepository.findByAircraftFleetId(aircraftFleetId))
                            .getFirst().getWeeklyFrequency();

            routeAircraftRepository.updateWeeklyFrequency(route,
                    aircraftInFleet, currentFrequency + weeklyFrequency);
        } else {
            // save the route to the aircraft
            RouteAircraft newRouteAircraft = RouteAircraft.builder()
                    .route(route)
                    .aircraftId(aircraftInFleet)
                    .player(player)
                    .weeklyFrequency(weeklyFrequency)
                    .build();

            routeAircraftRepository.save(newRouteAircraft);
        }
    }

    /**
     * Check if there are already flights scheduled for the given route and aircraft.
     * @param route The route to check.
     * @param aircraftFleetId The id of the aircraft in the player's fleet.
     * @return True if flights are already scheduled, otherwise, false.
     */
    private boolean areFlightsAlreadyScheduled(Route route, Long aircraftFleetId) {
        AircraftFleet aircraftFleet = aircraftFleetRepository.findByAircraftFleetId(aircraftFleetId);

        List<RouteAircraft> existingRoutes =
                routeAircraftRepository.findByRouteAndAircraftId(route
                        , aircraftFleet);

        return !existingRoutes.isEmpty();
    }

    /**
     * Calculate the total hours available in a week for an aircraft in a week.
     * @param aircraftFleetId The id of the aircraft in the player's fleet.
     * @return Integer representing the total hours available in a week for
     * the aircraft.
     */
    public Integer calculateHoursAvailable(Long aircraftFleetId) {
        List<AircraftRouteFlightTimeAndFrequencyDto> aircraftFlightSchedule =
                routeAircraftRepository.findAircraftWeeklyScheduleAndFlightTimes(aircraftFleetId);
        int totalTimeInAirPerWeek = 0;

        for (int i = 0; i < aircraftFlightSchedule.size(); i++) {
            Integer routeFlightTime = aircraftFlightSchedule.get(i).flightTime();
            Integer routeFrequency =
                    aircraftFlightSchedule.get(i).weeklyFrequency();
            totalTimeInAirPerWeek += routeFlightTime * routeFrequency;
        }

        // there are 10080 hours in a week
        return 10080 - totalTimeInAirPerWeek;
    }

    /**
     * Get all the flight schedules for a player's airline.
     * @param player
     * @return List of FlightSchedulesDto objects.
     */
    public List<FlightSchedulesDto> getScheduleForPlayer(Player player) {
        List<RouteAircraft> routeAircraft =
                routeAircraftRepository.findByPlayer(player);
        List<FlightSchedulesDto> flightSchedules = new ArrayList<>();

        for (RouteAircraft ra : routeAircraft) {
            Route route = ra.getRoute();
            List<AircraftFleetDto> aircraftOnCurrentRoute = new ArrayList<>();

            aircraftOnCurrentRoute.add(new AircraftFleetDto(
                    ra.getAircraftId().getAircraftFleetId(),
                    ra.getAircraftId().getAircraftType()
            ));

            flightSchedules.add(new FlightSchedulesDto(
                    new RouteDto(
                            route.getRouteId(),
                            route.getHubAirport(),
                            route.getDestinationAirport(),
                            route.getDistance(),
                            route.getTotalFlightTime(),
                            route.getPaxDemand()
                    ),
                    aircraftOnCurrentRoute,
                    ra.getWeeklyFrequency()
            ));
        }

        return flightSchedules;
    }
}
