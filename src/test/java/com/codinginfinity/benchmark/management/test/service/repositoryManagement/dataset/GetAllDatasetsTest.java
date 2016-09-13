package com.codinginfinity.benchmark.management.test.service.repositoryManagement.dataset;

import com.codinginfinity.benchmark.management.domain.Dataset;
import com.codinginfinity.benchmark.management.domain.DatasetCategory;
import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.repository.DatasetRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.dataset.DatasetManagement;
import com.codinginfinity.benchmark.management.test.service.repositoryManagement.GetAllRepoEntitiesTest;

import java.util.List;

/**
 * Created by andrew on 2016/08/30.
 */
public class GetAllDatasetsTest extends GetAllRepoEntitiesTest<DatasetCategory, Dataset,
        DatasetRepository,
        DatasetManagement> {

    @Override
    protected Long getExpectedId() {
        return null;
    }

    @Override
    protected String getExpectedName() {
        return null;
    }

    @Override
    protected String getExpectedDescription() {
        return null;
    }

    @Override
    protected User getExpectedUser() {
        return null;
    }

    @Override
    protected List<DatasetCategory> getExpectedCategories() {
        return null;
    }

    @Override
    protected Dataset getRepoEntity() {
        return new Dataset();
    }

    @Override
    protected String getNonExistentExceptionMessage() {
        return "Dataset does not Exist";
    }
}
