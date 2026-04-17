package practice.projects.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authenticator;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import practice.projects.platform.rest.RestResponse;
import practice.projects.platform.security.JwtTokenProvider;
import practice.projects.platform.security.PasswordHasher;
import practice.projects.platform.usecase.UseCase;
import practice.projects.repository.UserEntity;
import practice.projects.usecase.login.JwtTokenResponse;

import java.util.Collections;
import java.util.Optional;

/**
 * API Login Controller for JWT authentication
 */
@Controller("/api/auth")
public class ApiLoginController {
    private final Authenticator authenticator;
    private final JwtTokenProvider jwtTokenProvider;

    @Inject
    public ApiLoginController(Authenticator authenticator, JwtTokenProvider jwtTokenProvider) {
        this.authenticator = authenticator;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Login endpoint - returns JWT token
     * @param email user email
     * @param password user password
     * @return JWT token wrapped in RestResponse
     */
    @Post("/login")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse<?> login(@QueryValue String email, @QueryValue String password) {
        try {
            // Find user
            Optional<UserEntity> userOpt = UseCase.userList.stream()
                    .filter(u -> u.getEmail().equals(email))
                    .findFirst();

            if (userOpt.isEmpty()) {
                return HttpResponse.unauthorized()
                        .body(RestResponse.error("BA011", "Email not found"));
            }

            UserEntity user = userOpt.get();

            // Verify password
            if (!PasswordHasher.verifyPassword(password, user.getPassword())) {
                return HttpResponse.unauthorized()
                        .body(RestResponse.error("BA009", "Invalid credentials"));
            }

            // Generate JWT token
            String token = jwtTokenProvider.generateToken(email, Collections.singletonList(user.getRoles().toString()));

            JwtTokenResponse tokenResponse = new JwtTokenResponse(
                    token,
                    "Bearer",
                    email,
                    user.getRoles().toString(),
                    3600L
            );

            return HttpResponse.ok(RestResponse.success(tokenResponse));

        } catch (Exception e) {
            return HttpResponse.serverError()
                    .body(RestResponse.error("BA004", "Authentication failed: " + e.getMessage()));
        }
    }

    /**
     * Verify token endpoint
     */
    @Get("/verify")
    @Secured("BIDDER,SELLER,ADMIN")
    public HttpResponse<?> verifyToken() {
        return HttpResponse.ok(RestResponse.success("Token is valid"));
    }

    /**
     * Logout endpoint
     */
    @Post("/logout")
    @Secured("BIDDER,SELLER,ADMIN")
    public HttpResponse<?> logout() {
        // Token invalidation would typically be handled with a token blacklist
        return HttpResponse.ok(RestResponse.success("Logout successful"));
    }
}

