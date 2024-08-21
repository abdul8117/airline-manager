package com.abdul.airlinemanager.fleet;

import com.abdul.airlinemanager.player.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fleet")
@CrossOrigin(origins = "http://localhost:5173")
public class AircraftFleetController {
    private final AircraftFleetService aircraftFleetService;

    /**
     * Returns the aircraft in a player's fleet.
     * @param player
     * @return List of type PlayerFleetDto.
     */
    @GetMapping("")
    public List<AircraftFleetDto> getPlayerFleet(
            @AuthenticationPrincipal Player player
    ) {
        return aircraftFleetService.getPlayerFleet(player);
    }

    /**
     * Returns how many more hours a specific can fly for in a week.
     * @param aircraftFleetId id of an aircraft in the player's fleet
     * @return Integer number of hours available for the aircraft.
     */
    @GetMapping("/hours-available")
    public Integer getNumberOfHoursAvailable(@RequestParam Long aircraftFleetId) {
        return aircraftFleetService.getNumberOfHoursAvailable(aircraftFleetId);
    }
}
