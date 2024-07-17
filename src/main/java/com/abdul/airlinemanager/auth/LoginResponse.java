package com.abdul.airlinemanager.auth;

import lombok.Builder;

@Builder
public record LoginResponse(
    String jwtToken
) {
}
