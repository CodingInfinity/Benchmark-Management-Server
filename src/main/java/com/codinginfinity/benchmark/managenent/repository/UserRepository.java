package com.codinginfinity.benchmark.managenent.repository;

import com.codinginfinity.benchmark.managenent.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by andrew on 2016/06/15.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneById(Long id);

    Optional<User> findOneByUsername(String username);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByResetKey(String resetKey);

    Optional<User> findOneByActivationKey(String activationKey);
}
