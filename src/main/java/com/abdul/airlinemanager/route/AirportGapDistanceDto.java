package com.abdul.airlinemanager.route;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirportGapDistanceDto {
    public Data data;

    @Getter
    @Setter
    public static class Data {
        @JsonProperty("attributes")
        private Attributes attributes;
    }

    @Getter
    @Setter
    public static class Attributes {
        @JsonProperty("kilometers")
        private Double kilometers;
    }
}
