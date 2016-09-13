package com.codinginfinity.benchmark.management.repository;

import com.codinginfinity.benchmark.management.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository definition for the {@link User} object.
 *
 * @see com.codinginfinity.benchmark.management.domain.User
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneById(Long id);

    Optional<User> findOneByUsername(String username);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByResetKey(String resetKey);

    Optional<User> findOneByActivationKey(String activationKey);
}
