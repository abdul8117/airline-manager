package com.abdul.airlinemanager.route;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RouteAircraftId implements Serializable {
    private Long route;
    private Long aircraftId;
}
