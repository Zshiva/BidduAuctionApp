package practice.projects.platform.security;

import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import io.micronaut.core.async.publisher.Publishers;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import practice.projects.platform.usecase.UseCase;
import practice.projects.repository.UserEntity;

import java.util.Collections;
import java.util.Optional;

/**
 * Custom Authentication Provider for BidduAuctionApp
 * Validates user credentials against stored user list
 */
@Singleton
public class BidduAuthentication_Provider implements AuthenticationProvider, BidduAuthenticationProvider {

    @Inject
    public BidduAuthentication_Provider() {
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest,
                                                          AuthenticationRequest<?, ?> authenticationRequest) {
        String email = (String) authenticationRequest.getIdentity();
        String password = (String) authenticationRequest.getSecret();

        UserEntity user = UseCase.userList.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);

        if (user != null && PasswordHasher.verifyPassword(password, user.getPassword())) {
            UsernamePasswordCredentials userDetails = new UsernamePasswordCredentials(
                    email,
                    Collections.singletonList(user.getRoles().toString()).toString()
            );
            return Publishers.just((AuthenticationResponse) userDetails);
        }

        return Publishers.just(new AuthenticationResponse() {
            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public Optional<Authentication> getAuthentication() {
                return Optional.empty();
            }

            public java.util.Map<String, Object> getAttributes() {
                return null;
            }
        });
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(Object httpRequest, AuthenticationRequest authenticationRequest) {
        return null;
    }
}

