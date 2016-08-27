package com.codinginfinity.benchmark.management.repository;

import com.codinginfinity.benchmark.management.domain.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository definition for the {@link com.codinginfinity.benchmark.management.domain.Measurement} object.
 *
 * @see com.codinginfinity.benchmark.management.domain.Measurement
 *
 * @author Fabio Loreggian
 * @version 1.0.0
 */
@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Long>{
}


