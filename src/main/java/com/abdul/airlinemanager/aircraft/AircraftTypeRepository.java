package com.abdul.airlinemanager.aircraft;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftTypeRepository extends JpaRepository<AircraftType, Long> {

    AircraftType findByAircraftTypeId(Long aircraftTypeId);

}
