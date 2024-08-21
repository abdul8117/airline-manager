package com.abdul.airlinemanager.route;

import lombok.*;

import java.io.Serializable;

/**
 * This class allows the creation of a composite key for the RouteAircraft entity.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RouteAircraftId implements Serializable {
    private Long route;
    private Long aircraftId;
}
