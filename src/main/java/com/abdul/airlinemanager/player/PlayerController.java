package com.abdul.airlinemanager.player;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/player")
public class PlayerController {
    /**
     * Returns info about the player and their airline.
     * @param player
     * @return Player object
     */
    @PostMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public Player getPlayerAndAirlineInfo(@AuthenticationPrincipal Player player) {
        return player;
    }
}
