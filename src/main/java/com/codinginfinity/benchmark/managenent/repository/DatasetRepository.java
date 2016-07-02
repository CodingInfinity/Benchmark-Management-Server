package com.codinginfinity.benchmark.managenent.repository;

import com.codinginfinity.benchmark.managenent.domain.Dataset;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by reinhardt on 2016/06/25.
 */
@Repository
public interface DatasetRepository extends RepoEntityRepository<Dataset> {

    @Override
    @Query("select d.id, d.name, d.description, d.categories from Dataset d " +
            "inner join d.categories dc where dc.id = :categoryId")
    List<Dataset> findByCategory(@Param("categoryId") Long categoryId);
}
