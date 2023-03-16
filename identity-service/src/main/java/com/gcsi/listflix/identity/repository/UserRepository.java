package com.gcsi.listflix.identity.repository;

import com.gcsi.listflix.identity.domain.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

/**
 * @author Gary Cheng
 */
public interface UserRepository extends R2dbcRepository<User, Long> {
    /**
     * Find user by email.
     * @param email user's email
     * @return the user
     */
    Mono<User> findByEmail(String email);
}
