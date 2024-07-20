package com.abdul.airlinemanager.aircraft;

import com.abdul.airlinemanager.fleet.AircraftFleetService;
import com.abdul.airlinemanager.player.Player;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/aircraft-types")
@CrossOrigin(origins = "http://localhost:5173")
public class AircraftTypeController {

    private final AircraftTypeService aircraftTypeService;
    private final AircraftFleetService aircraftFleetService;

    @GetMapping("")
    public List<AircraftType> getAllAircraftTypes() {
        return aircraftTypeService.getAllAircraftTypes();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAircraftType(@RequestBody AircraftType aircraftType) {
        aircraftTypeService.addAircraftType(aircraftType);
    }

    @PostMapping("/buy?type={aircraftTypeId}")
    @ResponseStatus(HttpStatus.OK)
    public void buyAircraftType(
            @PathVariable Long aircraftTypeId,
            @AuthenticationPrincipal Player player
    ) {
        aircraftFleetService.buyAircraftType(aircraftTypeId, player);
    }

    @PostConstruct
    public void init() {
        AircraftType a320 = AircraftType.builder()
                .manufacturer(AircraftManufacturers.AIRBUS)
                .model(AircraftModels.A320neo)
                .capacity(180)
                .range(5000)
                .price(90000000L)
                .build();

        AircraftType a350 = AircraftType.builder()
                .manufacturer(AircraftManufacturers.AIRBUS)
                .model(AircraftModels.A350)
                .capacity(300)
                .range(15000)
                .price(300000000L)
                .build();

        AircraftType b737 = AircraftType.builder()
                .manufacturer(AircraftManufacturers.BOEING)
                .model(AircraftModels.B737)
                .capacity(150)
                .range(4500)
                .price(80000000L)
                .build();

        AircraftType b777 = AircraftType.builder()
                .manufacturer(AircraftManufacturers.BOEING)
                .model(AircraftModels.B777)
                .capacity(350)
                .range(14000)
                .price(250000000L)
                .build();

        aircraftTypeService.addAircraftType(a320);
        aircraftTypeService.addAircraftType(a350);
        aircraftTypeService.addAircraftType(b737);
        aircraftTypeService.addAircraftType(b777);
    }

}
