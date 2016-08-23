package com.codinginfinity.benchmark.management.repository;

import com.codinginfinity.benchmark.management.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

/**
 * Repository definition for the {@link Category} object.
 *
 * @see com.codinginfinity.benchmark.management.domain.Category
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@NoRepositoryBean
public interface CategoryRepository<T extends Category> extends JpaRepository<T, Long> {

    Optional<T> findOneByName(String name);

    Optional<T> findOneById(Long name);

    List<T> findAll();
}
