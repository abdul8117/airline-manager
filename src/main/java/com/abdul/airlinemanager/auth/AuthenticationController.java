package com.abdul.airlinemanager.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    /**
     * Registers a new user.
     * @param request
     * @return A response object that contains the user's JWT.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<RegistrationResponse> register(
            @RequestBody @Valid RegistrationRequest request
    ) {
        RegistrationResponse response = authenticationService.register(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Logs in a user.
     * @param request
     * @return A response object that contains the user's JWT.
     */
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<LoginResponse> login(
            @RequestBody @Valid LoginRequest request
    ) {
        LoginResponse response = authenticationService.login(request);
        return ResponseEntity.ok(response);
    }
}
