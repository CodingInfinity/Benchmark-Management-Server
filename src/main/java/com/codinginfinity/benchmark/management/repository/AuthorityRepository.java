package com.codinginfinity.benchmark.management.repository;

import com.codinginfinity.benchmark.management.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository definition for the {@link Authority} object.
 *
 * @see com.codinginfinity.benchmark.management.domain.Authority
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
