package com.gcsi.listflix.identity.repository;

import com.gcsi.listflix.identity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Gary Cheng
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find user by email.
     * @param email user's email
     * @return the user
     */
    User findByEmail(String email);

    /**
     * Find user by username.
     * @param username user's username
     * @return the user
     */
    User findByUsername(String username);
}
