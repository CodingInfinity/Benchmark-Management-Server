package com.codinginfinity.benchmark.management.service.repositoryManagement.dataset;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.management.service.repositoryManagement.AddRepoEntityTest;
import com.codinginfinity.benchmark.managenent.domain.Dataset;
import com.codinginfinity.benchmark.managenent.domain.DatasetCategory;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.DatasetRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset.DatasetManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 2016/06/25.
 */
public class UpdateDatasetMetadata extends AddRepoEntityTest<DatasetCategory, Dataset,
        DatasetRepository,
        DatasetManagement> {
    @Override
    protected Long getExpectedId() {
        return new Long(12345);
    }

    @Override
    protected String getExpectedName() {
        return new String("Sorting");
    }

    @Override
    protected String getExpectedDescription() {
        return new String("Numerical Data for Sorting");
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
        DatasetCategory sorting = new DatasetCategory(new Long(1), "Sorting");
        DatasetCategory ai = new DatasetCategory(new Long(2), "Artificial Intelligence");
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
