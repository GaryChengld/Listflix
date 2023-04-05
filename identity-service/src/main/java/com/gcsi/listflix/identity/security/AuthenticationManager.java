package com.gcsi.listflix.identity.security;

import com.gcsi.listflix.identity.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author Gary Cheng
 */
@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthenticationManager(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        return Mono.just(jwtTokenProvider.validateToken(authToken))
                .filter(valid -> valid)
                .switchIfEmpty(Mono.empty())
                .map(valid -> jwtTokenProvider.getAuthentication(authToken));
    }
}
