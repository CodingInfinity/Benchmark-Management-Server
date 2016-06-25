package com.codinginfinity.benchmark.managenent.repository;

import com.codinginfinity.benchmark.managenent.domain.DatasetCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andrew on 2016/06/25.
 */
@Repository
public interface DatasetCategoryRepository extends JpaRepository<DatasetCategory, Long> {
}
