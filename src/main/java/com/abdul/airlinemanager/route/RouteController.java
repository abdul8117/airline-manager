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

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Route> getRoutes() {
        return routeService.findAll();
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRoute(
            @RequestParam("destinationAirportId") Long destinationAirportId,
            @AuthenticationPrincipal Player player
    ) throws Exception {
        routeService.addRoute(destinationAirportId, player);
    }

    @GetMapping("/schedule")
    @ResponseStatus(HttpStatus.OK)
    public List<FlightSchedulesDto> getScheduleForPlayer(@AuthenticationPrincipal Player player) {
        return routeService.getScheduleForPlayer(player);
    }

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
