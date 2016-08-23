package com.codinginfinity.benchmark.management.repository;

import com.codinginfinity.benchmark.management.domain.Dataset;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository definition for the {@link Dataset} object.
 *
 * @see com.codinginfinity.benchmark.management.domain.Dataset
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@Repository
public interface DatasetRepository extends RepoEntityRepository<Dataset> {

    @Override
    @Query("select d.id, d.name, d.description, d.categories from Dataset d " +
            "inner join d.categories dc where dc.id = :categoryId")
    List<Dataset> findByCategory(@Param("categoryId") Long categoryId);
}
