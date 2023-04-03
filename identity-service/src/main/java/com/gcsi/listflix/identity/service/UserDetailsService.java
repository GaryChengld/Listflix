package com.gcsi.listflix.identity.service;

import com.gcsi.listflix.identity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author Gary Cheng
 */
@Component
public class UserDetailsService implements ReactiveUserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByEmail(username)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found")))
                .map(user -> org.springframework.security.core.userdetails.User.withUsername(username).authorities("USER").build());
    }
}
