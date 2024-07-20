package com.abdul.airlinemanager.route;

import com.abdul.airlinemanager.fleet.AircraftFleet;
import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a route-airplane relationship.
 * This class is used to store the weekly frequency of a specific aircraft on a
 * specific route.
 * The routeId and aircraftId are foreign keys that, together, form a
 * composite key for this entity.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

}
