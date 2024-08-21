package com.abdul.airlinemanager.aircraft;

import com.abdul.airlinemanager.auth.AuthenticationService;
import com.abdul.airlinemanager.fleet.AircraftFleetService;
import com.abdul.airlinemanager.player.Player;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/aircraft-types")
@CrossOrigin(origins = "http://localhost:5173")
public class AircraftTypeController {
    private static final Logger log =
            LoggerFactory.getLogger(AuthenticationService.class);

    private final AircraftTypeService aircraftTypeService;
    private final AircraftFleetService aircraftFleetService;

    /**
     * Returns all aircraft types.
     */
    @GetMapping("")
    public List<AircraftType> getAllAircraftTypes() {
        return aircraftTypeService.getAllAircraftTypes();
    }

    /**
     * Adds a new aircraft type to the database.
     * Note: Does not add it to a player's fleet but just the table
     * containing the different aircraft a player can buy.
     * @param aircraftType The aircraft to add of type AircraftType.
     */
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAircraftType(@RequestBody AircraftType aircraftType) {
        aircraftTypeService.addAircraftType(aircraftType);
    }

    /**
     * Buys an aircraft type for a player.
     * @param aircraftTypeId The id corresponding to the aircraft type.
     * @param player
     */
    @PostMapping("/buy")
    public void buyAircraftType(
            @RequestParam Long aircraftTypeId,
            @AuthenticationPrincipal Player player
    ) {
        log.info("Buying aircraft type with id: " + aircraftTypeId);
        aircraftFleetService.buyAircraftType(aircraftTypeId, player);
    }

    /**
     * Initialises the aircraft types in the database.
     */
    @PostConstruct
    public void init() {
        AircraftType a320 = AircraftType.builder()
                .manufacturer(AircraftManufacturers.AIRBUS)
                .model(AircraftModels.A320neo)
                .capacity(180)
                .range(5000)
                .price(90000000D)
                .build();

        AircraftType a350 = AircraftType.builder()
                .manufacturer(AircraftManufacturers.AIRBUS)
                .model(AircraftModels.A350)
                .capacity(300)
                .range(15000)
                .price(300000000D)
                .build();

        AircraftType b737 = AircraftType.builder()
                .manufacturer(AircraftManufacturers.BOEING)
                .model(AircraftModels.B737)
                .capacity(150)
                .range(4500)
                .price(80000000D)
                .build();

        AircraftType b777 = AircraftType.builder()
                .manufacturer(AircraftManufacturers.BOEING)
                .model(AircraftModels.B777)
                .capacity(350)
                .range(14000)
                .price(250000000D)
                .build();

        aircraftTypeService.addAircraftType(a320);
        aircraftTypeService.addAircraftType(a350);
        aircraftTypeService.addAircraftType(b737);
        aircraftTypeService.addAircraftType(b777);
    }
}
