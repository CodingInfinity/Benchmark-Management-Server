package com.codinginfinity.benchmark.management.repository;

import com.codinginfinity.benchmark.management.domain.AlgorithmCategory;
import org.springframework.stereotype.Repository;

/**
 * Repository definition for the {@link AlgorithmCategory} object.
 *
 * @see com.codinginfinity.benchmark.management.domain.AlgorithmCategory
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@Repository
public interface AlgorithmCategoryRepository extends CategoryRepository<AlgorithmCategory> {

}
