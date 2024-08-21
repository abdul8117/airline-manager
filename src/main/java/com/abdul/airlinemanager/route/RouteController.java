package com.abdul.airlinemanager.route;

import com.abdul.airlinemanager.player.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/routes")
@CrossOrigin(origins = "http://localhost:5173")
public class RouteController {
    private final RouteService routeService;

    /**
     * Add a new route to the player's airline.
     * Origin airport id is not needed as that is the player's hub airport.
     * @param destinationAirportId The id of the destination airport.
     * @param player
     * @throws Exception if the destination airport is not found.
     */
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRoute(
            @RequestParam("destinationAirportId") Long destinationAirportId,
            @AuthenticationPrincipal Player player
    ) throws Exception {
        routeService.addRoute(destinationAirportId, player);
    }

    /**
     * Gets the player's flight schedule detailing what planes are flying
     * where and how many times a week.
     * @param player
     * @return
     */
    @GetMapping("/schedule")
    @ResponseStatus(HttpStatus.OK)
    public List<FlightSchedulesDto> getScheduleForPlayer(@AuthenticationPrincipal Player player) {
        return routeService.getScheduleForPlayer(player);
    }

    /**
     * Adds flights to a route, or extra flights upon those already scheduled.
     * @param routeId The id of the route to add flights to.
     * @param aircraftFleetId The id of the aircraft in the player's fleet to
     *                        use.
     * @param weeklyFrequency The number of times a week to fly the route.
     * @param player
     */
    @PostMapping("/schedule")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFlightsToRoute(
            @RequestParam("routeId") Long routeId,
            @RequestParam("aircraftFleetId") Long aircraftFleetId,
            @RequestParam("weeklyFrequency") Integer weeklyFrequency,
            @AuthenticationPrincipal Player player
    ) {
        routeService.scheduleFlights(routeId, aircraftFleetId,
                weeklyFrequency, player);
    }
}
