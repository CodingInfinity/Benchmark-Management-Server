package com.codinginfinity.benchmark.management.repository;

import com.codinginfinity.benchmark.management.domain.DatasetCategory;
import org.springframework.stereotype.Repository;

/**
 * Repository definition for the {@link DatasetCategory} object.
 *
 * @see com.codinginfinity.benchmark.management.domain.DatasetCategory
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@Repository
public interface DatasetCategoryRepository extends CategoryRepository<DatasetCategory> {

}

