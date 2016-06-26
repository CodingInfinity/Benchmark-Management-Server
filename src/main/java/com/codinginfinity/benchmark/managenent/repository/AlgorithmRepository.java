package com.codinginfinity.benchmark.managenent.repository;

import com.codinginfinity.benchmark.managenent.domain.Algorithm;
import com.codinginfinity.benchmark.managenent.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by reinhardt on 2016/06/25.
 */
@Repository
public interface AlgorithmRepository extends JpaRepository<Algorithm, Long> {
    Optional<Algorithm> findOneById(Long id);

    List<Algorithm> findByName(String name);

    List<Algorithm> findByUser(String user);

    List<Algorithm> findByCategory(Category category);
}
