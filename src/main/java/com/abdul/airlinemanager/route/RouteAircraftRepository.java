package com.abdul.airlinemanager.route;

import com.abdul.airlinemanager.fleet.AircraftFleet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteAircraftRepository extends JpaRepository<RouteAircraft, Long>{

    @Query("SELECT new com.abdul.airlinemanager.route" +
            ".AircraftRouteFlightTimeAndFrequencyDto(route.totalFlightTime, " +
            "routeAircraft.weeklyFrequency) FROM RouteAircraft routeAircraft " +
            "JOIN Route route ON route.routeId = routeAircraft.routeId" +
            ".routeId WHERE routeAircraft.aircraftId.aircraftFleetId = ?1")
    List<AircraftRouteFlightTimeAndFrequencyDto> findAircraftWeeklyScheduleAndFlightTimes(Long aircraftId);

    List<RouteAircraft> findByRouteId(Route route);

    List<RouteAircraft> findByRouteIdAndAircraftId(Route route,
                                                      AircraftFleet aircraftFleet);

    @Modifying
    void updateWeeklyFrequency(Route route, Long aircraftFleetId, Integer weeklyFrequency);
}
