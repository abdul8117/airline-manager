package com.abdul.airlinemanager.route;

import com.abdul.airlinemanager.fleet.AircraftFleetDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FlightSchedulesDto {
    public RouteDto route;
    public List<AircraftFleetDto> aircraftFleet;
    public Integer weeklyFrequency;
}
