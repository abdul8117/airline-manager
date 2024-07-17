package com.abdul.airlinemanager.auth;

import lombok.Builder;

/**
 * Represents the response to a successful registration containing a JWT token
 * that can be used to authenticate the user in subsequent requests.
 *
 * This record is identical to LoginResponse but is kept separate for clarity
 * and to allow for future expansion.
 *
 * @param jwtToken
 */
@Builder
public record RegistrationResponse(
        String jwtToken
) {
}
