package com.abdul.airlinemanager.player;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public Player getPlayerAndAirlineInfo(@AuthenticationPrincipal Player player) {
        return player;
    }
}
