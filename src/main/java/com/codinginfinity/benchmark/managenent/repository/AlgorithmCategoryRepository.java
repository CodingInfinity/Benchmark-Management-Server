package com.codinginfinity.benchmark.managenent.repository;

import com.codinginfinity.benchmark.managenent.domain.AlgorithmCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by andrew on 2016/06/25.
 */
@Repository
public interface AlgorithmCategoryRepository extends JpaRepository<AlgorithmCategory, Long> {

    Optional<AlgorithmCategory> findOneByName(String name);
}
