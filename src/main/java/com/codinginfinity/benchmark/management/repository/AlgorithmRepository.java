package com.codinginfinity.benchmark.management.repository;

import com.codinginfinity.benchmark.management.domain.Algorithm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository definition for the {@link Algorithm} object.
 *
 * @see com.codinginfinity.benchmark.management.domain.Algorithm
 *
 * @author Andrew Broekman
 * @author Reinhardt Cromhout
 * @version 1.0.0
 */

@Repository
public interface AlgorithmRepository extends RepoEntityRepository<Algorithm> {

    @Override
    @Query("select a.id, a.name, a.description from Algorithm a " +
            "inner join a.categories ac where ac.id = :categoryId")
    List<Algorithm> findByCategory(@Param("categoryId") Long categoryId);
}
