package com.abdul.airlinemanager.auth;

import com.abdul.airlinemanager.airport.AirportRepository;
//import com.abdul.airlinemanager.email.EmailService;
//import com.abdul.airlinemanager.email.EmailTemplateName;
import com.abdul.airlinemanager.player.Player;
import com.abdul.airlinemanager.player.PlayerRepository;
import com.abdul.airlinemanager.role.RoleRepository;
import com.abdul.airlinemanager.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private static final Logger log =
            LoggerFactory.getLogger(AuthenticationService.class);

    private final RoleRepository roleRepository;
    private final AirportRepository airportRepository;
    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    /**
     * Registers a new player and returns a RegistrationResponse record
     * containing the player's JWT token.
     * @param registrationRequest
     * @return
     */
    public RegistrationResponse register(RegistrationRequest registrationRequest) {
        var userRole =
                roleRepository.findByName("USER").orElseThrow(() -> new IllegalStateException("Role USER not found"));

        var player = Player.builder()
                .email(registrationRequest.email())
                .password(passwordEncoder.encode(registrationRequest.password()))
                .airlineName(registrationRequest.airlineName())
                .airport(airportRepository.findByAirportId(registrationRequest.hubAirportId()))
                .balance(1000000000D)
                .roles(List.of(userRole))
                .build();

        playerRepository.save(player);

        log.info("New player registered: {}", player.getEmail());

        // generate a JWT token for the player
        var claims = new HashMap<String, Object>();
        claims.put("email", player.getEmail());
        var jwtToken = jwtService.generateToken(claims, player);

        return RegistrationResponse.builder().jwtToken(jwtToken).build();
    }

    /**
     * Authenticates the player and generates a JWT token
     * @param request the login request, includes email and password
     * @return a record containing the user's JWT token
     */
    public LoginResponse login(LoginRequest request) {
        // authenticate the player
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        log.info("Player {} logged in", request.email());

        // generate a JWT token for the player
        var claims = new HashMap<String, Object>();
        var player = (Player) auth.getPrincipal();
        claims.put("email", player.getEmail());
        var jwtToken = jwtService.generateToken(claims, player);

        return LoginResponse.builder().jwtToken(jwtToken).build();
    }
}
