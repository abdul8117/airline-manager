package com.abdul.airlinemanager.fleet;

import com.abdul.airlinemanager.player.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fleet")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AircraftFleetController {

    private final AircraftFleetService aircraftFleetService;

    @GetMapping("")
    public List<PlayerFleetDto> getPlayerFleet(
            @AuthenticationPrincipal Player player
    ) {
        return aircraftFleetService.getPlayerFleet(player);
    }

    @GetMapping("/hours-available")
    public Integer getNumberOfHoursAvailable(@RequestParam Long aircraftFleetId) {
        return aircraftFleetService.getNumberOfHoursAvailable(aircraftFleetId);
    }

}
