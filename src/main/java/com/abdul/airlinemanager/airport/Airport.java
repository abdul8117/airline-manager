package com.abdul.airlinemanager.airport;

import com.abdul.airlinemanager.city.City;
import com.abdul.airlinemanager.country.Country;
import jakarta.persistence.*;

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

    public Airport() {
    }

    public Airport(String name, String iataCode, String icaoCode, Integer weeklyGateCost, City city, Country country) {
        this.name = name;
        this.iataCode = iataCode;
        this.icaoCode = icaoCode;
        this.weeklyGateCost = weeklyGateCost;
        this.city = city;
        this.country = country;
    }

    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getIcaoCode() {
        return icaoCode;
    }

    public void setIcaoCode(String icaoCode) {
        this.icaoCode = icaoCode;
    }

    public Integer getWeeklyGateCost() {
        return weeklyGateCost;
    }

    public void setWeeklyGateCost(Integer weeklyGateCost) {
        this.weeklyGateCost = weeklyGateCost;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
