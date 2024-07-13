package com.abdul.airlinemanager.route;

import com.abdul.airlinemanager.fleet.AircraftFleet;
import jakarta.persistence.*;

/**
 * Represents a route-airplane relationship.
 * This class is used to store the weekly frequency of a specific aircraft on a
 * specific route.
 * The routeId and aircraftId are foreign keys that, together, form a
 * composite key for this entity.
 */

@Entity
@IdClass(RouteAircraftId.class)
public class RouteAircraft {

    @Id
    @OneToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route routeId;

    @Id
    @OneToOne
    @JoinColumn(name = "aircraft_id", nullable = false)
    private AircraftFleet aircraftId;

    @Column(nullable = false)
    private Integer weeklyFrequency;

    public RouteAircraft() {
    }

    public RouteAircraft(Route routeId, AircraftFleet aircraftId, Integer weeklyFrequency) {
        this.routeId = routeId;
        this.aircraftId = aircraftId;
        this.weeklyFrequency = weeklyFrequency;
    }

    public Route getRouteId() {
        return routeId;
    }

    public void setRouteId(Route routeId) {
        this.routeId = routeId;
    }

    public AircraftFleet getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(AircraftFleet aircraftId) {
        this.aircraftId = aircraftId;
    }

    public Integer getWeeklyFrequency() {
        return weeklyFrequency;
    }

    public void setWeeklyFrequency(Integer weeklyFrequency) {
        this.weeklyFrequency = weeklyFrequency;
    }
}
