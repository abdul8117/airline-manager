package com.abdul.airlinemanager.fleet;

import com.abdul.airlinemanager.aircraft.AircraftType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AircraftFleetDto {
    private Long aircraftFleetId;
    private AircraftType aircraftType;
}
