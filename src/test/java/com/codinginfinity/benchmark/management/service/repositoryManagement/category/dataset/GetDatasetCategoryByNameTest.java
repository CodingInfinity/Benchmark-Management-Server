package com.codinginfinity.benchmark.management.service.repositoryManagement.category.dataset;

import com.codinginfinity.benchmark.management.service.repositoryManagement.category.GetCategoryByNameTest;
import com.codinginfinity.benchmark.managenent.domain.DatasetCategory;
import com.codinginfinity.benchmark.managenent.repository.DatasetCategoryRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataset.DatasetCategoryManagement;
import org.springframework.boot.test.SpringApplicationConfiguration;

/**
 * Created by andrew on 2016/06/28.
 */
@SpringApplicationConfiguration(DatasetCategorySpringTest.class)
public class GetDatasetCategoryByNameTest extends GetCategoryByNameTest<DatasetCategory,
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
    protected String getDuplicateCategoryExceptionMessage() {
        return "Duplicate dataset category";
    }

    @Override
    protected String getNonExistentCategoryExceptionMessage() {
        return "Dataset category doesn't exist";
    }
}
