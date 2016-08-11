package com.codinginfinity.benchmark.management.repository;

import com.codinginfinity.benchmark.management.domain.Experiment;
import com.codinginfinity.benchmark.management.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by reinhardt on 2016/08/11.
 */
@Repository
public interface ExperimentRepository extends JpaRepository<Experiment, Long> {
    Optional<Experiment> findOneById(Long id);

    List<Experiment> findByUser(User user);

    List<Experiment> findByRequestedDate(ZonedDateTime requestedDate);

    List<Experiment> findByTimeout(Integer timeout);

    List<Experiment> findByProbeInterval(Integer probeInterval);
}
