package com.codinginfinity.benchmark.management.repository;

import com.codinginfinity.benchmark.management.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository definition for the {@link Job} object.
 *
 * @see com.codinginfinity.benchmark.management.domain.Job
 *
 * @author Andrew Broekman
 * @author Reinhardt Cromhout
 * @version 1.0.0
 */

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {

    Optional<Job> findOneById(Long id);
}
