package com.gcsi.listflix.identity.api;

import com.gcsi.listflix.identity.domain.User;
import com.gcsi.listflix.identity.repository.UserRepository;
import com.gcsi.listflix.identity.security.jwt.JwtTokenProvider;
import com.gcsi.listflix.identity.service.UserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author Gary Cheng
 */
@RestController
@RequestMapping("/api/v1/user")
@Validated
@Slf4j
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public Mono<AuthResponse> signupUser(@Valid @RequestBody SignupRequest request) {
        log.debug("request:{}", request);
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setAuthenticationProvider("local");
        return userRepository.save(user)
                .flatMap(u -> userDetailsService.findByUsername(u.getEmail()))
                .map(details -> new UsernamePasswordAuthenticationToken(details, details.getPassword(), details.getAuthorities()))
                .map(jwtTokenProvider::generateToken)
                .map(AuthResponse::new);
    }
}
