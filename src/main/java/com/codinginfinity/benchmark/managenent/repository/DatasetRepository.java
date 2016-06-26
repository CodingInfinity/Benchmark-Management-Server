package com.codinginfinity.benchmark.managenent.repository;

import com.codinginfinity.benchmark.managenent.domain.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by reinhardt on 2016/06/25.
 */
@Repository
public interface DatasetRepository extends JpaRepository<Dataset, Long> {
}
