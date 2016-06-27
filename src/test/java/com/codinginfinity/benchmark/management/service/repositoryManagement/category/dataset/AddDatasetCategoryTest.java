package com.codinginfinity.benchmark.management.service.repositoryManagement.category.dataset;

import com.codinginfinity.benchmark.management.service.repositoryManagement.category.AddCategoryTest;
import com.codinginfinity.benchmark.managenent.domain.DatasetCategory;
import com.codinginfinity.benchmark.managenent.repository.DatasetCategoryRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataset.DatasetCategoryManagement;

/**
 * Created by andrew on 2016/06/25.
 */
public class AddDatasetCategoryTest extends AddCategoryTest<DatasetCategory, DatasetCategoryRepository, DatasetCategoryManagement> {

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
}