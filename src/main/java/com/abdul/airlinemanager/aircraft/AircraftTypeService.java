package com.abdul.airlinemanager.aircraft;

import com.abdul.airlinemanager.fleet.AircraftFleet;
import com.abdul.airlinemanager.fleet.AircraftFleetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AircraftTypeService {

    private final AircraftTypeRepository aircraftTypeRepository;
    private final AircraftFleetService aircraftFleetService;

    public List<AircraftType> getAllAircraftTypes() {
        return aircraftTypeRepository.findAll();
    }

    public void addAircraftType(AircraftType aircraftType) {
        aircraftTypeRepository.save(aircraftType);
    }

    public void buyAircraftType(AircraftType aircraftType) {
        // add the aircraft to the player's fleet
        aircraftFleetService.addAircraftToFleet(aircraftType);
    }
}
