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
        AircraftType a320 = new AircraftType(
                AircraftManufacturers.AIRBUS,
                AircraftModels.A320,
                180,
                5700
        );

        AircraftType a350 = new AircraftType(
                AircraftManufacturers.AIRBUS,
                AircraftModels.A350,
                300,
                15000
        );

        AircraftType b737 = new AircraftType(
                AircraftManufacturers.BOEING,
                AircraftModels.B737,
                150,
                5000
        );

        AircraftType b777 = new AircraftType(
                AircraftManufacturers.BOEING,
                AircraftModels.B777,
                350,
                15000
        );

        aircraftTypeService.addAircraftType(a320);
        aircraftTypeService.addAircraftType(a350);
        aircraftTypeService.addAircraftType(b737);
        aircraftTypeService.addAircraftType(b777);
    }

}
