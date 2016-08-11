package com.codinginfinity.benchmark.management.repository;

import com.codinginfinity.benchmark.management.domain.Algorithm;
import com.codinginfinity.benchmark.management.domain.Dataset;
import com.codinginfinity.benchmark.management.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by reinhardt on 2016/08/11.
 */
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findOneById(Long id);

    List<Job> findByAlgorithm(Algorithm algorithm);

    List<Job> findByDataset(Dataset dataset);
}
