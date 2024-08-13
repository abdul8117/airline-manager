package com.abdul.airlinemanager.airport;

import com.abdul.airlinemanager.city.City;
import com.abdul.airlinemanager.country.Country;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long airportId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String iataCode; // IATA airport code

    private String icaoCode; // ICAO airport code

    @Column(nullable = false)
    private Integer weeklyGateCost;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

}
