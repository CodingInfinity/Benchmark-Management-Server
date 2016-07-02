package com.codinginfinity.benchmark.managenent.repository;

import com.codinginfinity.benchmark.managenent.domain.Algorithm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by reinhardt on 2016/06/25.
 */
@Repository
public interface AlgorithmRepository extends RepoEntityRepository<Algorithm> {

    @Override
    @Query("select a.id, a.name, a.description, a.categories from Algorithm a " +
            "inner join a.categories ac where ac.id = :categoryId")
    List<Algorithm> findByCategory(@Param("categoryId") Long categoryId);
}
