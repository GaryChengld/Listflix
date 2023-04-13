package com.gcsi.listflix.identity.api;

import com.gcsi.listflix.identity.api.exception.UserAlreadyExistsException;
import com.gcsi.listflix.identity.domain.User;
import com.gcsi.listflix.identity.repository.UserRepository;
import com.gcsi.listflix.identity.security.jwt.JwtTokenProvider;
import com.gcsi.listflix.identity.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author Gary Cheng
 */
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserController {
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final ReactiveAuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public Mono<ApiResponse<AuthData>> signupUser(@Valid @RequestBody SignupRequest request) {
        log.debug("Signup request:{}", request);
        return userRepository.findByEmail(request.getEmail())
                .hasElement()
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new UserAlreadyExistsException("User with email already exists"));
                    } else {
                        return userRepository.save(this.toUser(request))
                                .flatMap(u -> userDetailsService.findByUsername(u.getEmail()))
                                .map(details -> new UsernamePasswordAuthenticationToken(details, details.getPassword(), details.getAuthorities()))
                                .map(jwtTokenProvider::generateToken)
                                .map(AuthData::new)
                                .map(ApiResponse::withData);
                    }
                });
    }

    @PostMapping("/signIn")
    public Mono<ApiResponse<AuthData>> signIn(@Valid @RequestBody SignInRequest request) {
        log.debug("signIn request:{}", request);
        return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()))
                .map(jwtTokenProvider::generateToken)
                .map(AuthData::new)
                .map(ApiResponse::withData);
    }

    @GetMapping("/me")
    public Mono<ApiResponse<User>> me(@AuthenticationPrincipal Authentication authentication) {
        log.debug("me authentication:{}", authentication);
        return this.userRepository.findByEmail(authentication.getName()).map(ApiResponse::withData);
    }

    private User toUser(SignupRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setAuthenticationProvider("local");
        return user;
    }
}
