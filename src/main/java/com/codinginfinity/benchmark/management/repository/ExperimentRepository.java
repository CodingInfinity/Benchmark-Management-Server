package com.codinginfinity.benchmark.management.repository;

import com.codinginfinity.benchmark.management.domain.Experiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by fabio on 2016/08/13.
 */
@Repository
public interface ExperimentRepository extends JpaRepository<Experiment, Long>{
}
