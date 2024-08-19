package com.abdul.airlinemanager.route;

import com.abdul.airlinemanager.fleet.AircraftFleet;
import com.abdul.airlinemanager.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteAircraftRepository extends JpaRepository<RouteAircraft, Long>{

    @Query("SELECT new com.abdul.airlinemanager.route" +
            ".AircraftRouteFlightTimeAndFrequencyDto(route.totalFlightTime, " +
            "routeAircraft.weeklyFrequency) FROM RouteAircraft routeAircraft " +
            "JOIN Route route ON route.routeId = routeAircraft.route" +
            ".routeId WHERE routeAircraft.aircraftId.aircraftFleetId = ?1")
    List<AircraftRouteFlightTimeAndFrequencyDto> findAircraftWeeklyScheduleAndFlightTimes(Long aircraftId);

    List<RouteAircraft> findByRouteAndAircraftId(Route route,
                                                      AircraftFleet aircraftFleet);

    @Modifying
    @Query("UPDATE RouteAircraft ra SET ra.weeklyFrequency = ?3 WHERE ra.route = ?1 AND ra.aircraftId = ?2")
    void updateWeeklyFrequency(Route route, AircraftFleet aircraftFleet,
                               Integer weeklyFrequency);

    List<RouteAircraft> findByPlayer(Player player);

}
