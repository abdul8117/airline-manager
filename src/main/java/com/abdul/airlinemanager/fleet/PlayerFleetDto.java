package com.abdul.airlinemanager.fleet;

import com.abdul.airlinemanager.aircraft.AircraftType;

public record PlayerFleetDto(
        AircraftType aircraftType,
        Long quantity
) {
}
