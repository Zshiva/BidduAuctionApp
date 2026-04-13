package practice.projects.platform.security;

import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import org.reactivestreams.Publisher;

public interface BidduAuthenticationProvider extends AuthenticationProvider {
    Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest,
                                                   AuthenticationRequest<?, ?> authenticationRequest);
}
