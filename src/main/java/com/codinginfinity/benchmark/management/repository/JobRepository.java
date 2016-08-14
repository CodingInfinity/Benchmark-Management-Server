package com.codinginfinity.benchmark.management.repository;

import com.codinginfinity.benchmark.management.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by fabio on 2016/08/13.
 */
@Repository
public interface JobRepository extends JpaRepository<Job,Long> {

    Optional<Job> findOneById(Long id);
}
