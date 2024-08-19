package com.abdul.airlinemanager.route;

import com.abdul.airlinemanager.airport.Airport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RouteDto {
    private Long routeId;
    private Airport hubAirport;
    private Airport destinationAirport;
    private Integer distance;
    private Integer totalFlightTime;
    private Integer paxDemand;
}
