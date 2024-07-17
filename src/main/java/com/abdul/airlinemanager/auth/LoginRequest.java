package com.abdul.airlinemanager.auth;

import jakarta.validation.constraints.Email;

public record LoginRequest(
        @Email
        String email,

        String password
) {
}
