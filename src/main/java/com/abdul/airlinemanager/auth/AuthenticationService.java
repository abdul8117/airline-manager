package com.abdul.airlinemanager.auth;

import com.abdul.airlinemanager.airport.AirportRepository;
import com.abdul.airlinemanager.email.EmailService;
import com.abdul.airlinemanager.email.EmailTemplateName;
import com.abdul.airlinemanager.player.Player;
import com.abdul.airlinemanager.player.PlayerRepository;
import com.abdul.airlinemanager.player.Token;
import com.abdul.airlinemanager.player.TokenRepository;
import com.abdul.airlinemanager.role.RoleRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final AirportRepository airportRepository;
    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    public void register(RegistrationRequest registrationRequest) throws MessagingException {
        var userRole =
                roleRepository.findByName("USER").orElseThrow(() -> new IllegalStateException("Role USER not found"));

        var player = Player.builder()
                .email(registrationRequest.email())
                .password(passwordEncoder.encode(registrationRequest.password()))
                .airlineName(registrationRequest.airlineName())
                .airport(airportRepository.findByAirportId(registrationRequest.hubAirportId()))
                .balance(1000000000L)
                .accountLocked(false)
                .enabled(false)
                .build();

        playerRepository.save(player);

        sendValidationEmail(player);

    }

    private void sendValidationEmail(Player player) throws MessagingException {

        var newToken = generateAndSaveActivationToken(player);

        emailService.sendEmail(
                player.getEmail(),
                player.getAirlineName(),
                "Activate your account",
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken
        );

    }

    private String generateAndSaveActivationToken(Player player) {
        String generatedToken = generateActivationToken(6);

        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .player(player)
                .build();

        tokenRepository.save(token);

        return generatedToken;
    }

    private String generateActivationToken(int length) {
        String characters = "0123456789";
        StringBuilder token = new StringBuilder(length);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++)
            token.append(characters.charAt(random.nextInt(characters.length())));

        return token.toString();
    }


}
