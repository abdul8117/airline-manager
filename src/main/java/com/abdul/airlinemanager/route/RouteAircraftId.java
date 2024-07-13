package com.abdul.airlinemanager.route;

import java.io.Serializable;
import java.util.Objects;

public class RouteAircraftId implements Serializable {
    private Long routeId;
    private Long aircraftId;

    public RouteAircraftId() {
    }

    public RouteAircraftId(Long routeId, Long aircraftId) {
        this.routeId = routeId;
        this.aircraftId = aircraftId;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Long getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Long aircraftId) {
        this.aircraftId = aircraftId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RouteAircraftId that)) return false;
        return Objects.equals(getRouteId(), that.getRouteId()) && Objects.equals(getAircraftId(), that.getAircraftId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRouteId(), getAircraftId());
    }
}
