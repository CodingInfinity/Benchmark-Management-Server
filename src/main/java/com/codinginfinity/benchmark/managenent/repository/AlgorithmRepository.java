package com.codinginfinity.benchmark.managenent.repository;

import com.codinginfinity.benchmark.managenent.domain.Algorithm;
import com.codinginfinity.benchmark.managenent.domain.Category;
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
    @Query("select id, name, user, description, categories from Algorithm a " +
            "inner join algorithm_algorithmCategory ac where a.id = ac.algorithm_id and ac.category_id = :categoryId")
    List<Algorithm> findByCategory(@Param("categoryId") Long categoryId);
}
