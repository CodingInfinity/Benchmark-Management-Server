package com.codinginfinity.benchmark.management.repository;

import com.codinginfinity.benchmark.management.domain.Experiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository definition for the {@link Experiment} object.
 *
 * @see com.codinginfinity.benchmark.management.domain.Experiment
 *
 * @author Andrew Broekman
 * @author Reinhardt Cromhout
 * @version 1.0.0
 */

@Repository
public interface ExperimentRepository extends JpaRepository<Experiment, Long>{

    Optional<Experiment> findOneById(Long id);
}
