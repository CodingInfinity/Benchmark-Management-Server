package com.codinginfinity.benchmark.management.test.service.repositoryManagement.dataset;

import com.codinginfinity.benchmark.management.domain.Dataset;
import com.codinginfinity.benchmark.management.domain.DatasetCategory;
import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.repository.DatasetRepository;
import com.codinginfinity.benchmark.management.service.repositoryManagement.dataset.DatasetManagement;
import com.codinginfinity.benchmark.management.test.service.repositoryManagement.GetRepoEntityByUsernameTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 2016/06/25.
 */
public class GetDatasetByUsernameTest extends GetRepoEntityByUsernameTest<DatasetCategory, Dataset,
        DatasetRepository,
        DatasetManagement> {
    protected String getNonExistentExceptionMessage() {
        return "Dataset does not Exist";
    }

    @Override
    protected Long getExpectedId() {
        return 12345L;
    }

    @Override
    protected String getExpectedName() {
        return "Sorting";
    }

    @Override
    protected String getExpectedDescription() {
        return "Numerical Data for Sorting";
    }

    @Override
    protected User getExpectedUser() {
        User user = new User();
        user.setUsername("johndoe");
        user.setPassword("p@$$w0rd");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setActivated(false);
        user.setResetDate(null);
        user.setResetKey(null);
        return user;
    }

    @Override
    protected List<DatasetCategory> getExpectedCategories() {
        List<DatasetCategory> categories = new ArrayList<DatasetCategory>();
        DatasetCategory sorting = new DatasetCategory(1L, "Sorting");
        DatasetCategory ai = new DatasetCategory(2L, "Artificial Intelligence");
        categories.add(sorting);
        categories.add(ai);
        return categories;
    }

    @Override
    protected Dataset getRepoEntity() {
        Dataset ds = new Dataset();
        ds.setName(getExpectedName());
        ds.setUser(getExpectedUser());
        ds.setDescription(getExpectedDescription());
        ds.setId(getExpectedId());
        ds.setCategories(getExpectedCategories());
        return ds;
    }
}
