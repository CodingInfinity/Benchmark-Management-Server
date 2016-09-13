package com.codinginfinity.benchmark.management.test.service.repositoryManagement.category.algorithm;

import com.codinginfinity.benchmark.management.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.management.repository.AlgorithmCategoryRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.algorithm.AlgorithmCategoryManagement;
import com.codinginfinity.benchmark.management.test.service.repositoryManagement.category.GetCategoryByIdTest;
import org.springframework.boot.test.SpringApplicationConfiguration;

/**
 * Created by andrew on 2016/06/25.
 */
@SpringApplicationConfiguration(AlgorithmCategorySpringTest.class)
public class GetAlgorithmCategoryByIdTest extends GetCategoryByIdTest<AlgorithmCategory,
        AlgorithmCategoryRepository,
        AlgorithmCategoryManagement> {

    @Override
    protected Long getExpectedId() {
        return 1L;
    }

    @Override
    protected String getExpectedName() {
        return "Test";
    }

    @Override
    protected AlgorithmCategory getCategory() {
        return new AlgorithmCategory(getExpectedId(), getExpectedName());
    }

    @Override
    protected AlgorithmCategory getNewCategory(Long id, String name) {
        return new AlgorithmCategory(id, name);
    }

    @Override
    protected String getDuplicateCategoryExceptionMessage() {
        return "Duplicate algorithm category";
    }

    @Override
    protected String getNonExistentCategoryExceptionMessage() {
        return "Algorithm category doesn't exist";
    }
}
