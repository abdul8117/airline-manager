package com.abdul.airlinemanager.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(
        @Email(message = "Email address is not valid")
        String email,

        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password,

        String airlineName,

        Long hubAirportId
) {
}
