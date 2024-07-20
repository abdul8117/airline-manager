package com.abdul.airlinemanager.aircraft;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aircraft-types")
@CrossOrigin(origins = "http://localhost:5173")
public class AircraftTypeController {

    private final AircraftTypeService aircraftTypeService;

    public AircraftTypeController(AircraftTypeService aircraftTypeService) {
        this.aircraftTypeService = aircraftTypeService;
    }

    @GetMapping("")
    public List<AircraftType> getAllAircraftTypes() {
        return aircraftTypeService.getAllAircraftTypes();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAircraftType(@RequestBody AircraftType aircraftType) {
        aircraftTypeService.addAircraftType(aircraftType);
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
