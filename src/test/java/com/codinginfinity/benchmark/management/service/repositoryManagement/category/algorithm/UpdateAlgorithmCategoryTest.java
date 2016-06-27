package com.codinginfinity.benchmark.management.service.repositoryManagement.category.algorithm;

import com.codinginfinity.benchmark.management.service.repositoryManagement.category.UpdateCategoryTest;
import com.codinginfinity.benchmark.managenent.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.managenent.repository.AlgorithmCategoryRepository;
import com.codinginfinity.benchmark.managenent.repository.CategoryRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm.AlgorithmCategoryManagement;

/**
 * Created by andrew on 2016/06/25.
 */
public class UpdateAlgorithmCategoryTest extends UpdateCategoryTest<AlgorithmCategory, AlgorithmCategoryRepository, AlgorithmCategoryManagement> {

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
    protected AlgorithmCategoryRepository getRepo() {
        return null;
    }
}
