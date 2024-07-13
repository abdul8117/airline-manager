package com.abdul.airlinemanager.aircraft;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftTypeService {

    private final AircraftTypeRepository aircraftTypeRepository;

    public AircraftTypeService(AircraftTypeRepository aircraftTypeRepository) {
        this.aircraftTypeRepository = aircraftTypeRepository;
    }

    public List<AircraftType> getAllAircraftTypes() {
        return aircraftTypeRepository.findAll();
    }

    public void addAircraftType(AircraftType aircraftType) {
        aircraftTypeRepository.save(aircraftType);
    }

}
