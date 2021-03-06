package com.codinginfinity.benchmark.management.test.service.repositoryManagement.category.dataset;

import com.codinginfinity.benchmark.management.domain.DatasetCategory;
import com.codinginfinity.benchmark.management.repository.DatasetCategoryRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.dataset.DatasetCategoryManagement;
import com.codinginfinity.benchmark.management.test.service.repositoryManagement.category.UpdateCategoryTest;
import org.springframework.boot.test.SpringApplicationConfiguration;

/**
 * Created by andrew on 2016/06/25.
 */
@SpringApplicationConfiguration(DatasetCategorySpringTest.class)
public class UpdateDatasetCategoryTest extends UpdateCategoryTest<DatasetCategory,
        DatasetCategoryRepository,
        DatasetCategoryManagement> {

    @Override
    protected Long getExpectedId() {
        return 1L;
    }

    @Override
    protected String getExpectedName() {
        return "Test";
    }

    @Override
    protected DatasetCategory getCategory() {
        return new DatasetCategory(getExpectedId(), getExpectedName());
    }

    @Override
    protected DatasetCategory getNewCategory(Long id, String name) {
        return new DatasetCategory(id, name);
    }

    @Override
    protected String getDuplicateCategoryExceptionMessage() {
        return "Duplicate dataset category";
    }

    @Override
    protected String getNonExistentCategoryExceptionMessage() {
        return "Dataset category doesn't exist";
    }
}
