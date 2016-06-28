package com.codinginfinity.benchmark.management.service.repositoryManagement.category.dataset;

import com.codinginfinity.benchmark.management.service.repositoryManagement.category.AddCategoryTest;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.GetCategoryByIdTest;
import com.codinginfinity.benchmark.managenent.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.managenent.domain.DatasetCategory;
import com.codinginfinity.benchmark.managenent.repository.DatasetCategoryRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataset.DatasetCategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataset.exception.DuplicateDatasetCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataset.exception.NonExistentDatasetCategoryException;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by andrew on 2016/06/25.
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DatasetCategorySpringTest.class)
public class GetDatasetCategoryByIdTest extends GetCategoryByIdTest<DatasetCategory,
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
