package com.abdul.airlinemanager.aircraft;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AircraftTypeService {
    private final AircraftTypeRepository aircraftTypeRepository;

    /**
     * Queries the database for all available aircraft types.
     * @return List of AircraftType objects.
     */
    public List<AircraftType> getAllAircraftTypes() {
        return aircraftTypeRepository.findAll();
    }

    /**
     * Adds a new aircraft type to the database.
     * @param aircraftType The type of aircraft to be added.
     */
    public void addAircraftType(AircraftType aircraftType) {
        aircraftTypeRepository.save(aircraftType);
    }
}
